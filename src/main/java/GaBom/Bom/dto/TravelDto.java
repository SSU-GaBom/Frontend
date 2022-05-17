package GaBom.Bom.dto;

import GaBom.Bom.entity.Pin;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
public class TravelDto {

    private Long travelId;

    private String title;
    private String state;
    private String city;

    // 입력이 String -> LocalDatetime 형으로 변환해서 들어가도록
    private String startDate;

    private String endDate;

    private List<Pin> pinList;

    private Integer expense;
    private String content;

//    private List<TravelImageDto> base64Images;

    private String transportation;
    public TravelDto(Long travelId, String title, String state, String city, String startDate, String endDate, List<Pin> pinList, Integer expense, String content) {
        this.travelId = travelId;
        this.title = title;
        this.state = state;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pinList = pinList;
        this.expense = expense;
        this.content = content;
    }

}
