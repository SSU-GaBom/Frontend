package GaBom.Bom.component;

import GaBom.Bom.advice.exception.CExtensionException;
import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileHandler {
    public Image parseFileInfo(User user, MultipartFile profileImage){
        if(profileImage.isEmpty())
            return null;

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = new File("").getAbsolutePath() + "\\";

        String path = user.getUserId() + "/profile";
        File file = new File(path);

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
        return Image.builder()
                .original_file_name(profileImage.getOriginalFilename())
                .stored_file_path(path + "/" + new_file_name)
                .file_size(profileImage.getSize())
                .user(user)
                .build();
    }
}
