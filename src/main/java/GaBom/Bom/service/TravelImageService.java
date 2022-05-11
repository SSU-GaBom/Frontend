package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CImageNotFoundException;
import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.component.TravelFileHandler;
import GaBom.Bom.entity.*;
import GaBom.Bom.repository.TravelImageRepository;
import GaBom.Bom.repository.TravelRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelImageService {
    private final TravelImageRepository travelImageRepository;
    private final UserRepository userRepository;
    private final TravelFileHandler travelFileHandler;

    @Transactional // TravelImageService에 있어야함.
    public void RegistTravelImage(String nickName, MultipartFile ImageFile, Travel travel, Pin pin) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByNickName(nickName).orElseThrow(CUserNotFoundException::new);

        if(!user.getUserId().equals(authentication.getName()))
            throw new CNotSameUserException();

        TravelImage Image =  travelFileHandler.parseTravelFileInfo(user, ImageFile,travel,pin);

        log.info("imageBuild complete");

        //
        List<TravelImage> travelImages = pin.getTravelImages();
        if(travelImages == null){ //새로 등록
            log.info("Travelimage file is null");
            travelImageRepository.save(Image);
            for (TravelImage travelImage : travelImages) {
                travelImage.setPin(pin);
            }
            pin.setTravelImages(travelImages);
//            user.setProfileImage(Image);
            List<TravelImage> travelImages1 = pin.getTravelImages();
            for (TravelImage travelImage : travelImages1) {
                System.out.println("travelImage = " + travelImage);
            }
//            log.info(pin.getTravelImage().getOriginal_file_name());
        }
        else{
            log.info("image file is not null");
            //TODO : 업데이트할때, 기존 List를 들고와서 수정해야함. 빡센디?
//            TravelImage currentProfileImage = TravelImageRepository.findByUser(user).orElseThrow(CImageNotFoundException::new);
//            log.info(currentProfileImage.getOriginal_file_name());
//            currentProfileImage.updateProfileImage(profileImage.getOriginal_file_name(), profileImage.getStored_file_path(), profileImage.getFile_size());
        }

        log.info(user.getProfileImage().getOriginal_file_name());
        System.out.println("우선 Image 등록 끝");
        return ;
    }
}
