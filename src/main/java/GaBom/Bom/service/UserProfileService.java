package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.entity.User;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public User showInfo(String userId, String loginUserId){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        return new User();
    }

    public void updateProfile(String userId, Authentication authentication){
//        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
//        UserDto userDto
//        User.builder()
//                //.userId(userDto.getUserId())
//                .userPw(userDto.getUserPw())
//                .userName(userDto.getUserName())
//                .nickName(userDto.getNickName())
//                .profileImage(userDto.getProfileImage())
//                .build();
    }
}
