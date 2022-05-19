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
    public String NickName;
    @NotNull
    public Integer FollowerNums;

    public RankingFollowDto(User user) {
        NickName = user.getNickName();
        FollowerNums = user.getFollwerNum();
    }
}
