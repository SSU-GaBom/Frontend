package GaBom.Bom.dto;

import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.Pin;
import GaBom.Bom.entity.Transportation;
import GaBom.Bom.entity.Travel;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
public class TravelDto {

    private Long travelId;

    private String title;
    private String state;
    private String city;

    // 입력이 String -> LocalDatetime 형으로 변환해서 들어가도록
    private String startDate;

    private String endDate;

    private List<Pin> pinList;

    public TravelDto(String title, String state, String city, String startDate, String endDate, List<Pin> pinList) {
//        this.travelId = travelId;
        this.title = title;
        this.state = state;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pinList = pinList;
    }

    //    private Integer expense;
//    private String content;
//    private Transportation transportation;
}
