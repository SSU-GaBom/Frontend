package GaBom.Bom.dto;

//import GaBom.Bom.entity.Image;
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

    private String userId; //유저의 id임. rkskek
    private Long travelId;
    private String title;
    private String state;
    private String city;
    private String startDate;
    private String endDate;

    private List<Pin> pinList;
    private Integer expense;
    private String content;

    private Integer likedCount;
    private String transportation;


    public GetTravelDto(Travel travel){
        this.userId=travel.getMyuser().getUserId();
        this.travelId = travel.getTravelId();
        this.title = travel.getTitle();
        this.state = travel.getState();
        this.city = travel.getCity();
        this.startDate = travel.getStartDate();
        this.endDate = travel.getEndDate();
        this.pinList = travel.getPinList();
        this.expense = travel.getExpense();
        this.content = travel.getContent();
        this.likedCount=travel.getLikedCount();
        this.transportation=travel.getTransportation();
    }


}
