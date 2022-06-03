package GaBom.Bom.component;

import GaBom.Bom.advice.exception.CExtensionException;
import GaBom.Bom.entity.ProfileImage;
import GaBom.Bom.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class FileHandler {

//    private String path = "/Volumes/SSD/School/";

    //private String path = "C:/Users/psg98/GaBom/image/";
//    private String path = "C:/Users/sion/Desktop/";
    private String path = "/root/profile_image";

    public ProfileImage parseFileInfo(User user, MultipartFile profileImage) throws IOException {
        if(profileImage.isEmpty()) {
            log.info("null 인가?");
            return null;
        }

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        String path = this.path + user.getUserId() + "/profile";
        File file = new File(path);

        log.info(path);

        if(!file.exists())
            file.mkdirs();

        String contentType = profileImage.getContentType();
        String originalFileExtension;

        if(contentType.contains("image/jpeg")){
            originalFileExtension =".jpg";
        }else if(contentType.contains("image/png")){
            originalFileExtension = ".png";
        }else if(contentType.contains("image/gif")){
            originalFileExtension = ".gif";
        }else
            throw new CExtensionException();

        String new_file_name = user.getUserId() + "_profile" + originalFileExtension;

        file = new File(path + File.separator + new_file_name);
        profileImage.transferTo(file);

        log.info(file.getPath());

        return ProfileImage.builder()
                .original_file_name(profileImage.getOriginalFilename())
                .stored_file_path(path + "/" + new_file_name)
                .file_size(profileImage.getSize())
                .user(user)
                .build();
    }

    public byte[] getProfileImageByte(ProfileImage profileImage) throws IOException {
        File file = new File(profileImage.getStored_file_path());
        if(!file.exists()) {
            System.out.println(" file dont exist");
            return null;
        }
        return Files.readAllBytes(file.toPath());
    }
}