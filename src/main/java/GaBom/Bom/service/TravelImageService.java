package GaBom.Bom.service;

import GaBom.Bom.advice.exception.CImageNotFoundException;
import GaBom.Bom.advice.exception.CNotSameUserException;
import GaBom.Bom.advice.exception.CUserNotFoundException;
import GaBom.Bom.dto.PinDto;
import GaBom.Bom.dto.TravelImageDto;
import GaBom.Bom.entity.*;
import GaBom.Bom.repository.PinRepository;
import GaBom.Bom.repository.TravelImageRepository;
import GaBom.Bom.repository.TravelRepository;
import GaBom.Bom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getFullPath;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelImageService {
    private final TravelImageRepository travelImageRepository;
    private final UserRepository userRepository;
//    private final TravelFileHandler travelFileHandler;
    private final PinRepository pinRepository;

    @Value("C:/User/Desktop/")
    private String imageDir;

    @Transactional
    public void createPin(Pin pin, List<TravelImage> base64Images) throws IOException {
        log.info("ImageService : createPin");
        //이미지를 실제로 저장하고 이미지객체를 아이템과 연관시키기
        for (TravelImage base64Image : base64Images) {
            TravelImage image = travelImage(base64Image);
            pin.setTravelImage(image);
        }

        pinRepository.save(pin);
    }

    public TravelImage travelImage(TravelImage image) throws IOException
    {
        log.info("ImageService : travelImage");
        String travelImageName = createTravelFileName(image.getImageName());
        File imagefile = new File(getFullPath(travelImageName));
        byte[] decodeBytes = Base64.getDecoder().decode(image.getBase64Image().getBytes());
        FileOutputStream fos = new FileOutputStream(imagefile);
        fos.write(decodeBytes);
        fos.close();

        return TravelImage.builder()
                    .uploadImageName(image.getImageName())
                    .travelImageName(travelImageName)
                    .build();
    }

    private String createTravelFileName(String originalImageName) {
        //String ext = extractExt(originalImageName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + "png";
    }




//    @Transactional // TravelImageService에 있어야함.
//    public void RegistTravelImage(String nickName, MultipartFile ImageFile, Travel travel, Pin pin) throws IOException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByNickName(nickName).orElseThrow(CUserNotFoundException::new);
//
//        if(!user.getUserId().equals(authentication.getName()))
//            throw new CNotSameUserException();
//
//        TravelImage Image =  travelFileHandler.parseTravelFileInfo(user, ImageFile,travel,pin);
//
//        log.info("imageBuild complete");
//
//        //
//        List<TravelImage> travelImages = pin.getTravelImages();
//        if(travelImages == null){ //새로 등록
//            log.info("Travelimage file is null");
//            travelImageRepository.save(Image);
//            for (TravelImage travelImage : travelImages) {
//                travelImage.setPin(pin);
//            }
//            pin.setTravelImages(travelImages);
////            user.setProfileImage(Image);
//            List<TravelImage> travelImages1 = pin.getTravelImages();
//            for (TravelImage travelImage : travelImages1) {
//                System.out.println("travelImage = " + travelImage);
//            }
////            log.info(pin.getTravelImage().getOriginal_file_name());
//        }
//        else{
//            log.info("image file is not null");
//            //TODO : 업데이트할때, 기존 List를 들고와서 수정해야함. 빡센디?
////            TravelImage currentProfileImage = TravelImageRepository.findByUser(user).orElseThrow(CImageNotFoundException::new);
////            log.info(currentProfileImage.getOriginal_file_name());
////            currentProfileImage.updateProfileImage(profileImage.getOriginal_file_name(), profileImage.getStored_file_path(), profileImage.getFile_size());
//        }
//
//        log.info(user.getProfileImage().getOriginal_file_name());
//        System.out.println("우선 Image 등록 끝");
//        return ;
//    }
}
