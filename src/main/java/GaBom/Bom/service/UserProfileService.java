package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.dto.UserDto;
import GaBom.Bom.dto.UserProfileDto;
import GaBom.Bom.entity.User;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileDto showInfo(String userId, String loginUserId){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.builder()
                .loginUser(loginUserId)
                .follow()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .profileImage(user.getProfileImage())
//                .userFollowerCount()
//                .userFollowingCount()
                .myTravelList(user.getMyTravelList())
                .likedTravelList(user.getLikedTravelList())
                .storedTravelList(user.getStoredTravelList())
                .build();
        if(userId.equals(loginUserId))
            userProfileDto.setIsMe(true);
        else
            userProfileDto.setIsme(false);

        if()
    }

    public void updateProfile(String userId, Authentication authentication){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        user.builder()
                //.userId(userDto.getUserId())
                .profileImage(userDto.getProfileImage())
                .build();
    }
}