package GaBom.Bom.service;


import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.dto.FindUserDto;
import GaBom.Bom.dto.UserAuthDto;
import GaBom.Bom.entity.ConfirmationToken;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.repository.ConfirmationTokenRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FindUserService {

    private final UserRepository userRepository;
    private final CheckService checkService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService; // API 요청 결과에 대한 code, message

    //return true면 유저가 존재해서 정상적으로 메일이 보내진거 false면 유저가 없거나 메일이 보내지지 않은 것
    @Transactional
    public String findId(FindUserDto findUserDto){
        if(checkService.checkUserNameAndEmail(findUserDto.getUserName(), findUserDto.getEmail()))
            throw new CUserNotFoundException();
        return userRepository.findByUserNameAndEmail(findUserDto.getUserName(), findUserDto.getEmail()).orElseThrow().getUserId();
    }

    @Transactional
    public CommonResult findPassword(FindUserDto findUserDto){
        String userId = findUserDto.getUserId();
        String email = findUserDto.getEmail();

        if(checkService.checkUserIdAndEmail(userId, email))
            throw new CUserNotFoundException();
        emailService.setMessage(userId, email, "http://localhost:8080/finduser/changepw?token=");
        return responseService.getSuccessResult();
        //return userRepository.findByUserIdAndEmail(findUserDto.getUserId(), findUserDto.getEmail()).orElseThrow().getUserId();
    }

    //넘겨 받은 객체로 이메일을 통해 유저 정보를 얻어서 비밀번호를 업데이트한다.
    @Transactional
    public boolean setPassword(String password, User user){
        userRepository.updatePassWord(passwordEncoder.encode(password), user.getUserNo());

        if(passwordEncoder.matches(password, user.getUserPw()))
            return true;

        return false;
    }
}
