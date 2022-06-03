package GaBom.Bom.dto;

import GaBom.Bom.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RankingFollowDto {
    @NotNull
    public String nickName;
    @NotNull
    public Integer followerNums;

    public RankingFollowDto(User user) {
        nickName = user.getNickName();
        followerNums = user.getFollowerNum();
    }
}
