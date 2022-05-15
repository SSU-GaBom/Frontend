package GaBom.Bom.dto;

import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.Pin;
import GaBom.Bom.entity.Transportation;
import GaBom.Bom.entity.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
public class GetTravelDto {

    private Long travelId;
    private String title;
    private String state;
    private String city;
    private String startDate;
    private String endDate;
    private List<Pin> pinList;
    private Integer expense;
    private String content;

    private String transportation;

    public GetTravelDto(Long travelId, String title, String state, String city, String startDate, String endDate, List<Pin> pinList, Integer expense, String content) {
        this.travelId = travelId;
        this.title = title;
        this.state = state;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pinList = pinList;
        this.expense = expense;
        this.content = content;
        this.transportation=null;
    }

    public GetTravelDto(Travel travel){
        this.travelId = travel.getTravelId();
        this.title = travel.getTitle();
        this.state = travel.getState();
        this.city = travel.getCity();
        this.startDate = travel.getStartDate();
        this.endDate = travel.getEndDate();
        this.pinList = travel.getPinList();
        this.expense = travel.getExpense();
        this.content = travel.getContent();
        this.transportation=travel.getTransportation();
    }


}
