package GaBom.Bom.dto;

import GaBom.Bom.entity.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RankingRecommendDto {
    @NotNull
    public String recommendTravelTitle;
    @NotNull
    public Long recommendTravelId;
    @NotNull
    public Integer recommendNums;

    public RankingRecommendDto(Travel travel) {
        this.recommendTravelTitle = travel.getTitle();
        this.recommendTravelId = travel.getTravelId();
        this.recommendNums = travel.getLikedCount();
    }
}
