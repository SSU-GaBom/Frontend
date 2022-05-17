package GaBom.Bom.dto;

import GaBom.Bom.entity.ProfileImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowDto {

    private long id;
    private ProfileImage profileImage;
    private String name;
    private String loginUser;
    private int followState;
}
