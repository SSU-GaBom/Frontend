package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.component.FileHandler;
import GaBom.Bom.configuration.security.JwtTokenProvider;
import GaBom.Bom.dto.UserProfileDto;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final ResponseService responseService;
    private final FileHandler fileHandler;

    public SingleResult showInfo(String nickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginUserId = authentication.getName();

        User user = userRepository.findByNickName(nickName).orElseThrow(CUserNotFoundException::new);
        String profileId = user.getUserId();

        UserProfileDto userProfileDto = new UserProfileDto();

        if(profileId.equals(loginUserId))
            userProfileDto.setMe(true);
        else
            userProfileDto.setMe(false);

        return responseService.getSingleResult(
                userProfileDto.builder()
                .loginUser(loginUserId)
                .userId(user.getUserId())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .profileImage(user.getProfileImage())
                .userFollowerCount(user.getFollwerNum())
                .userFollowingCount(user.getFollowingNum())
//                .myTravelList(user.getMyTravelList())
                //.likedTravelList(user.getLikedTravelList())
//                .storedTravelList(user.getStoredTravelList())
                .build());

    }

    @Transactional
    public SingleResult updateProfile(String nickName, MultipartFile profileImage){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByNickName(nickName).orElseThrow(CUserNotFoundException::new);

        if(!user.getUserId().equals(authentication.getName()))
            throw new CNotSameUserException();

        user.setProfileImage(fileHandler.parseFileInfo(user, profileImage));
        return responseService.getSingleResult(user.getProfileImage().getStored_file_path());
    }
}