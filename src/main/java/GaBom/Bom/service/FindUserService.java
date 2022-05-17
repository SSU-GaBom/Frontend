package GaBom.Bom.service;


import GaBom.Bom.advice.exception.CRandomIntNotExistsException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.component.CreateRandomInt;
import GaBom.Bom.dto.FindUserDto;
import GaBom.Bom.dto.UpdatePasswordDto;
import GaBom.Bom.entity.PasswordToken;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.repository.PasswordTokenRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FindUserService {

    private final UserRepository userRepository;
    private final CheckService checkService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService; // API 요청 결과에 대한 code, message
    private final CreateRandomInt createRandomInt;
    private final PasswordTokenRepository passwordTokenRepository;

    //return true면 유저가 존재해서 정상적으로 메일이 보내진거 false면 유저가 없거나 메일이 보내지지 않은 것
    @Transactional
    public String findId(FindUserDto findUserDto){
        if(checkService.checkUserNameAndEmail(findUserDto.getUserName(), findUserDto.getEmail()))
            throw new CUserNotFoundException();
        return userRepository.findByUserNameAndEmail(findUserDto.getUserName(), findUserDto.getEmail()).orElseThrow().getUserId();
    }
   @Transactional
   public CommonResult sendRandomInt(FindUserDto findUserDto){
        String userId = findUserDto.getUserId();
        String email = findUserDto.getEmail();

        String randomIntString;

       if(checkService.checkUserIdAndEmail(userId, email))
           throw new CUserNotFoundException();

       Long userNo = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new).getUserNo();

       randomIntString = createRandomInt.createRandomInt(userNo);

       if(passwordTokenRepository.existsByUserNo(userNo)){
           PasswordToken passwordToken = passwordTokenRepository.findByUserNo(userNo).orElseThrow(CRandomIntNotExistsException::new);
           PasswordToken.updatePasswordToken(passwordToken, randomIntString);
       }else{
           PasswordToken passwordToken = PasswordToken.createPasswordToken(userNo, randomIntString);
           passwordTokenRepository.save(passwordToken);
       }

       emailService.intAuth(email, randomIntString);

       return responseService.getSuccessResult();
   }

   @Transactional
   public CommonResult authenticationRandomInt(String randomIntString, FindUserDto findUserDto){
        String isMatched = "불일치 합니다.";

        String userId = findUserDto.getUserId();

        Long userNo = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new).getUserNo();

        PasswordToken pstoken = passwordTokenRepository.findByUserNo(userNo).orElseThrow(CRandomIntNotExistsException::new);

        if(pstoken.isExpired())
            return responseService.getSingleResult("랜덤 난수가 만료되었습니다.");
        else if(pstoken.getExpirationDate().isBefore(LocalDateTime.now()))
            return responseService.getSingleResult("랜덤 난수가 만료되었습니다.");

        if(randomIntString.equals(pstoken.getRandomInt())) {
            isMatched = "일치합니다.";
            pstoken.setExpired(true);
        }

        return responseService.getSingleResult(isMatched);
   }

    //넘겨 받은 객체로 이메일을 통해 유저 정보를 얻어서 비밀번호를 업데이트한다.
    @Transactional
    public boolean updatePassword(UpdatePasswordDto updatePasswordDto){
        User user = userRepository.findByUserId(updatePasswordDto.getUserId()).orElseThrow(CUserNotFoundException::new);
        String password = updatePasswordDto.getPassword();

        user.updatePassword(passwordEncoder.encode(password));

        if(passwordEncoder.matches(password, user.getUserPw()))
            return true;

        return false;
    }
}
