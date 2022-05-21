package GaBom.Bom.dto;

//import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.Pin;
import GaBom.Bom.entity.Transportation;
import GaBom.Bom.entity.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class GetTravelDto {

    private String userId; //유저의 id임. rkskek

    private String userNickname;
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

    private Integer zzimCount;
    private String transportation;

    private boolean IsLike;
    private boolean IsZzim;

    public GetTravelDto(Travel travel){
        this.userNickname=travel.getMyuser().getNickName();
        this.travelId = travel.getTravelId();
        this.title = travel.getTitle();
        this.state = travel.getState();
        this.city = travel.getCity();
        this.startDate = travel.getStartDate();
        this.endDate = travel.getEndDate();
        this.pinList = travel.getPinList();
        this.expense = travel.getExpense();
        this.content = travel.getContent();
        this.zzimCount=travel.getZzimCount();
        this.likedCount=travel.getLikedCount();
        this.transportation=travel.getTransportation();
    }


}
