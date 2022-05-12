//package GaBom.Bom.component;
//
//import GaBom.Bom.advice.exception.CExtensionException;
//import GaBom.Bom.entity.*;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//@Slf4j
//public class TravelFileHandler {
//
////    private String path = "/Volumes/SSD/School/";
//
//    private String path = "C:/Users/psg98/GaBom/image/";
//
//    public TravelImage parseTravelFileInfo(User user, MultipartFile Image, Travel travel, Pin pin) throws IOException {
//        if(Image.isEmpty()) {
//            log.info("Image Is null");
//            return null;
//        }
//
//        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String current_date = simpleDateFormat.format(new Date());
//
//
//        //:TODO  :: 각 travel별로 증가하게 저장하기
//        //계속 저장하면서, 만약 파일이 존재하면 1 증가하면서 저장(?)
//        String path = this.path + user.getUserId() + "/travel/"+travel.getTravelId()+"/";
//        //travel별로 같은 이미지일수 있으니까 경로를 바꿔가며 해야함
//        File file = new File(path);
//
//        log.info(path);
//
//        if(!file.exists())
//            file.mkdirs();
//
//        String contentType = Image.getContentType();
//        String originalFileExtension;
//
//        if(contentType.contains("image/jpeg")){
//            originalFileExtension =".jpg";
//        }else if(contentType.contains("image/png")){
//            originalFileExtension = ".png";
//        }else if(contentType.contains("image/gif")){
//            originalFileExtension = ".gif";
//        }else
//            throw new CExtensionException();
//
//        //TODO:: 파일명 정하기
//        String new_file_name = user.getUserId() + "_travel" +travel.getTravelId()+ originalFileExtension;
//
//        file = new File(path + File.separator + new_file_name);
//        Image.transferTo(file);
//
//        log.info(file.getPath());
//
//        return TravelImage.builder()
//                .original_file_name(Image.getOriginalFilename())
//                .stored_file_path(path + "/" + new_file_name)
//                .file_size(Image.getSize())
//                .pin(pin)
//                .build();
//    }
//
//    public byte[] getTravelImageByte(Image image) throws IOException {
//        File file = new File(image.getStored_file_path());
//        if(!file.exists())
//            return null;
//        return Files.readAllBytes(file.toPath());
//    }
//}