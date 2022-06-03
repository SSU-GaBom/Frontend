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

    @Transactional
    public void joinUser(SignUpUserDto signUpUserDto) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        Date time = new Date();
        String localTime = format.format(time);

        userRepository.save(User.builder()
                .userId(signUpUserDto.getUserId())
                .userPw(passwordEncoder.encode(signUpUserDto.getUserPw()))
                .userName(signUpUserDto.getUserName())
                .email(signUpUserDto.getEmail())
                .nickName(signUpUserDto.getNickName())
                        .followerNum(0)
                        .followingNum(0)
                .appendDate(localTime)
                .updateDate(localTime)
                .provider(null)
                .emailAuth(false)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }
}
