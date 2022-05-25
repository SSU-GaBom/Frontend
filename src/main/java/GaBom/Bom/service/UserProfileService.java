package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CImageNotFoundException;
import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.component.FileHandler;
import GaBom.Bom.dto.GetTravelDto;
import GaBom.Bom.dto.GetTravelDtoWithImages;
import GaBom.Bom.dto.UserProfileDto;
import GaBom.Bom.dto.UserTravelDto;
import GaBom.Bom.entity.*;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.repository.ProfileImageRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;

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
            ProfileImage profileImage = user.getProfileImage();
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
//                .myTravelList(user.getMyTravelList())
//                .likedTravelList(user.getLikedTravelList())

                .build();
        List<GetTravelDtoWithImages> mytravellists = MyTravelsByUserWithImages(user);
        userProfileDto.setMyTravelList(mytravellists);

//        List<UserTravelDto> imagesdto = new ArrayList<>();
//        for (GetTravelDto mytravellist : mytravellists) {
//            UserTravelDto tmpDto= new UserTravelDto();
//            List<String> tmpimages= new ArrayList<>();
//            tmpDto.setTravelId(mytravellist.getTravelId());
//            List<Pin> pinList = mytravellist.getPinList();
//            for (Pin pin : pinList) {
//                List<TravelImage> images = pin.getImages();
//                for (TravelImage image : images) {
//                    tmpimages.add(image.getBase64Image());
//                }
//                tmpDto.setImages(tmpimages);
//            }
//            imagesdto.add(tmpDto);
//        }
//        userProfileDto.setMyTravelimages(imagesdto);


//        List<GetTravelDto> liketravellists = MyLikeTravels(user);
//        userProfileDto.setLikedTravelList(liketravellists);

        if(profileId.equals(loginUserId))
            userProfileDto.setMe(true);
        else
            userProfileDto.setMe(false);

        return responseService.getSingleResult(userProfileDto);
    }


    @Transactional
    public List<GetTravelDto> MyTravelsByUser(User user){
        List<Travel> myTravelList = user.getMyTravelList();
        List<GetTravelDto> lists= new ArrayList<>();
        for (Travel travel : myTravelList) {
            List<Pin> pinList = travel.getPinList();
            Hibernate.initialize(pinList); //정보확인
            for (Pin pin : pinList) {
                Hibernate.initialize(pin.getImages());
            }
            Hibernate.initialize(travel.getPinList());
            //lazy
            lists.add(new GetTravelDto(travel));
        }
        return lists;
    }

    @Transactional
    public List<GetTravelDtoWithImages> MyTravelsByUserWithImages(User user){
        List<Travel> myTravelList = user.getMyTravelList();
        List<GetTravelDtoWithImages> lists= new ArrayList<>();
        List<String> images=new ArrayList<>();
        for (Travel travel : myTravelList) {
            List<Pin> pinList = travel.getPinList();
            Hibernate.initialize(pinList); //정보확인
            for (Pin pin : pinList) {
                Hibernate.initialize(pin.getImages());
                List<TravelImage> images1 = pin.getImages();
                for (TravelImage travelImage : images1) {
                    images.add(travelImage.getBase64Image());
                }
            }
            Hibernate.initialize(travel.getPinList());
            //lazy
            lists.add(new GetTravelDtoWithImages(travel,images));
        }
        return lists;
    }



    @Transactional
    public List<GetTravelDto> MyLikeTravels(User user){
//        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        List<Travel> LikeTravelList = user.getLikedTravelList();
        System.out.println(" 좋아요 list 출력");
        for (Travel travel : LikeTravelList) {
            System.out.println("travel.getTitle() = " + travel.getTitle());
        }
        //없으면??
        List<GetTravelDto> lists= new ArrayList<>();
        for (Travel travel : LikeTravelList) {
            List<Pin> pinList = travel.getPinList();
            Hibernate.initialize(pinList); //정보확인
            for (Pin pin : pinList) {
                Hibernate.initialize(pin.getImages());
            }
            Hibernate.initialize(travel.getPinList());
            lists.add(new GetTravelDto(travel));
        }
        return lists;
    }





    @Transactional
    public SingleResult updateProfile(String nickName, MultipartFile profileImageFile) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByNickName(nickName).orElseThrow(CUserNotFoundException::new);

        if(!user.getUserId().equals(authentication.getName()))
            throw new CNotSameUserException();

        ProfileImage profileImage =  fileHandler.parseFileInfo(user, profileImageFile);

        log.info("imageBuild complete");

        if(user.getProfileImage() == null){
            log.info("image file is null");
            profileImageRepository.save(profileImage);
            user.setProfileImage(profileImage);
            log.info(user.getProfileImage().getOriginal_file_name());
        }
        else{
            log.info("image file is not null");
            ProfileImage currentProfileImage = profileImageRepository.findByUser(user).orElseThrow(CImageNotFoundException::new);
            currentProfileImage.updateProfileImage(profileImage.getOriginal_file_name(), profileImage.getStored_file_path(), profileImage.getFile_size());
            log.info(currentProfileImage.getOriginal_file_name());
            currentProfileImage.updateProfileImage(profileImage.getOriginal_file_name(), profileImage.getStored_file_path(), profileImage.getFile_size());
        }

        log.info(user.getProfileImage().getOriginal_file_name());

        return responseService.getSingleResult("update completed");
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
        profileImageRepository.deleteByUser(user);
        return responseService.getSuccessResult();
    }
}