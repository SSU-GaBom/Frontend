package GaBom.Bom.dto;

//import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.Pin;
import GaBom.Bom.entity.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class GetTravelDtoWithImages {

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

//    private Integer commentCount;

    private boolean IsLike;
    private boolean IsZzim;
    private List<String> images;

    public GetTravelDtoWithImages(Travel travel,List<String> images){
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
        this.images=images;
    }

}
