package GaBom.Bom.service;


import GaBom.Bom.advice.exception.CEmailAuthTokenNotFoundException;
import GaBom.Bom.advice.exception.CSigninFailedException;
import GaBom.Bom.configuration.security.JwtTokenProvider;
import GaBom.Bom.dto.LoginDto;
import GaBom.Bom.dto.TokenUserIdDto;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogInService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider; // jwt 토큰 생성
    private final ResponseService responseService; // API 요청 결과에 대한 code, message
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    @Transactional
    public SingleResult<TokenUserIdDto> signIn(LoginDto loginDto){
        //입력받은 아이디와 비밀번호
        String id = loginDto.getLoginId();
        String password = loginDto.getLoginPw();

        log.info("id : {}" , id);
        log.info("password : {}" , password);
        User user = userRepository.findByUserId(id).orElseThrow(CSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            // matches : 평문, 암호문 패스워드 비교 후 boolean 결과 return
            throw new CSigninFailedException();
        }
        if(user.getEmailAuth() == false){
            throw new CEmailAuthTokenNotFoundException();
        }

        log.info("signIn4");

        TokenUserIdDto tokenUserIdDto = new TokenUserIdDto(jwtTokenProvider.createToken(String.valueOf(user.getUserId()), user.getRoles()), user.getUserId());

        return responseService.getSingleResult(tokenUserIdDto);
    }
}
