package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CImageNotFoundException;
import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.component.FileHandler;
import GaBom.Bom.configuration.security.JwtTokenProvider;
import GaBom.Bom.dto.UserProfileDto;
import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.User;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.ImageRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final ResponseService responseService;
    private final FileHandler fileHandler;

    @Transactional
    public SingleResult showInfo(String nickName) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginUserId = authentication.getName();

        User user = userRepository.findByNickName(nickName).orElseThrow(CUserNotFoundException::new);
        String profileId = user.getUserId();
        byte profileImageByte[];

        try {
            Image profileImage = user.getProfileImage();
            profileImageByte = fileHandler.getProfileImageByte(user.getProfileImage());
        }catch (NullPointerException e){
            profileImageByte = null;
        }

        UserProfileDto userProfileDto = UserProfileDto.builder()
                        .loginUser(loginUserId)
                        .userId(user.getUserId())
                        .userName(user.getUserName())
                        .nickName(user.getNickName())
                        .profileImage(profileImageByte)
                        .userFollowerCount(user.getFollwerNum())
                        .userFollowingCount(user.getFollowingNum())
                        //.myTravelList(user.getMyTravelList())
                        //.likedTravelList(user.getLikedTravelList())
                        //.storedTravelList(user.getStoredTravelList())
                        .build();

        if(profileId.equals(loginUserId))
            userProfileDto.setMe(true);
        else
            userProfileDto.setMe(false);

        return responseService.getSingleResult(userProfileDto);
    }

    @Transactional
    public SingleResult updateProfile(String nickName, MultipartFile profileImageFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByNickName(nickName).orElseThrow(CUserNotFoundException::new);

        if(!user.getUserId().equals(authentication.getName()))
            throw new CNotSameUserException();

        Image profileImage =  fileHandler.parseFileInfo(user, profileImageFile);

        log.info("imageBuild complete");

        if(user.getProfileImage() == null){
            log.info("image file is null");
            imageRepository.save(profileImage);
            user.setProfileImage(profileImage);
            log.info(user.getProfileImage().getOriginal_file_name());
        }
        else{
            log.info("image file is not null");
            Image currentProfileImage = imageRepository.findByUser(user).orElseThrow(CImageNotFoundException::new);
            currentProfileImage.updateProfileImage(profileImage.getOriginal_file_name(), profileImage.getStored_file_path(), profileImage.getFile_size());
            log.info(currentProfileImage.getOriginal_file_name());
        }

        log.info(user.getProfileImage().getOriginal_file_name());

        return responseService.getSingleResult(user.getProfileImage().getStored_file_path());
    }

    @Transactional
    public CommonResult deleteProfile(String nickName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByNickName(nickName).orElseThrow(CUserNotFoundException::new);

        if(!user.getUserId().equals(authentication.getName()))
            throw new CNotSameUserException();

        if(user.getProfileImage() == null){
            throw new CImageNotFoundException();
        }

        user.setProfileImage(null);
        imageRepository.deleteByUser(user);
        return responseService.getSuccessResult();
    }
}