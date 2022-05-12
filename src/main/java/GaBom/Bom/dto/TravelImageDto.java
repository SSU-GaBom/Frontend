package GaBom.Bom.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TravelImageDto {

    private String imageName;
    private String base64Image;

}

