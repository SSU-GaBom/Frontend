package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.component.FileHandler;
import GaBom.Bom.dto.UserDto;
import GaBom.Bom.dto.UserProfileDto;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final ResponseService responseService;
    private final FileHandler fileHandler;

    public SingleResult showInfo(String userId, String loginUserId){
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        UserProfileDto userProfileDto = new UserProfileDto();

        if(userId.equals(loginUserId))
            userProfileDto.setMe(true);
        else
            userProfileDto.setMe(false);

        userProfileDto.builder()
                .loginUser(loginUserId)
                .userId(user.getUserId())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .profileImage(user.getProfileImage())
                .userFollowerCount(user.getFollwerNum())
                .userFollowingCount(user.getFollowingNum())
                .myTravelList(user.getMyTravelList())
                .likedTravelList(user.getLikedTravelList())
                .storedTravelList(user.getStoredTravelList())
                .build();


        return responseService.getSingleResult(userProfileDto);
    }

    public void updateProfile(User user, MultipartFile profileImage){

        user.setProfileImage(fileHandler.parseFileInfo(user.getUserId(), profileImage));

//        user.builder()
//                //.userId(userDto.getUserId())
//                .profileImage(userDto.getProfileImage())
//                .build();
    }
}