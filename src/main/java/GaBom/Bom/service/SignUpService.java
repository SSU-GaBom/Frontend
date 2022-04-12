package GaBom.Bom.service;


import GaBom.Bom.dto.SignUpUserDto;
import GaBom.Bom.dto.UserDto;
import GaBom.Bom.entity.User;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
    Date time = new Date();
    String localTime = format.format(time);

    @Transactional
    public boolean checkId(String userId) {
        //아이디가 존재하면 생성 불가
        if (userRepository.existsByUserId(userId))
            return false;
        return true;
    }



    @Transactional
    public void joinUser(SignUpUserDto signUpUserDto) {

        userRepository.save(User.builder()
                .userId(signUpUserDto.getUserId())
                .userPw(passwordEncoder.encode(signUpUserDto.getUserPw()))
                .userName(signUpUserDto.getUserName())
                .email(signUpUserDto.getEmail())
                .nickName(signUpUserDto.getNickName())
                .appendDate(localTime)
                .updateDate(localTime)
                .provider(null)
                .emailAuth(false)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }
}
