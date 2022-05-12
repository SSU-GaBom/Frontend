package GaBom.Bom.dto;

import GaBom.Bom.entity.Pin;

import java.util.ArrayList;
import java.util.List;

public class PinDto {
    private Long id;
    private List<TravelImageDto> base64Images;

//    public Pin toEntity(){
//        return Pin.builder()
//                .id(id)
//                .imageList(new ArrayList<>())
//                .build();
//    }
//
//    public void setImageDtoList(List<ImageDto> imageDtoList){
//        this.base64Images = new ArrayList<>();
//
//        for (ImageDto imageDto : imageDtoList) {
//            this.base64Images.add(imageDto);
//        }
}
