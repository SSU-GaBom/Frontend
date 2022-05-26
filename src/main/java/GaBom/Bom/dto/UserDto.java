package GaBom.Bom.dto;

import GaBom.Bom.entity.ProfileImage;
import GaBom.Bom.entity.Travel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    private ProfileImage profileImage;
    private Integer following;
    private List<String> roles;
    private List<Travel> myTravelList;
    private List<Travel> likedTravelList;

}
