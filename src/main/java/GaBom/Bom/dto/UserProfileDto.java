package GaBom.Bom.dto;

import GaBom.Bom.entity.Image;
import GaBom.Bom.entity.StoredTravel;
import GaBom.Bom.entity.Travel;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {
    private String loginUser;
    private boolean follow;
    private boolean isMe;
    private String userId;
    private String userName;
    private String nickName;
    private Image profileImage;
    private int userFollowerCount;
    private int userFollowingCount;
    //내가 쓴 리뷰 리스트
    private List<Travel> myTravelList = new ArrayList<>();

    //찜을 누른 리뷰 리스트
    private List<Travel> likedTravelList = new ArrayList<>();

    //내가 분류하여 저장한 리뷰 리스트(분류 자체로 리스트여야 하고 분류 후에도 리스트여야 해서 고민 해야함.)
    private List<StoredTravel> storedTravelList = new ArrayList<>();
}
