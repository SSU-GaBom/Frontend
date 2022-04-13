package GaBom.Bom.dto;

import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.Travel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDto {

    private Long userNo;
    private String userId;
    private String userPw;
    private String email;
    private String userName;
    private String nickName;
    private String appendDate;
    private String updateDate;
    private Image profileImage;
    private Integer following;
    private List<String> roles;
    private List<Travel> myTravelList;
    private List<Travel> likedTravelList;

    //내가 분류하여 저장한 리뷰 리스트(분류 자체로 리스트여야 하고 분류 후에도 리스트여야 해서 고민 해야함.)
    /*
    @OneToMany
    @JoinColumn(name = "stored_travel_id")
    private List<StoredTravel> storedTravelList;
     */

}
