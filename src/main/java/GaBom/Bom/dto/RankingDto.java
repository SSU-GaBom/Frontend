package GaBom.Bom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RankingDto {
    //1) Zzim
    List<RankingZzimDto> rankingZzimDtos = new ArrayList<>();

    List<RankingFollowDto> rankingFollowDto = new ArrayList<>();

    List<RankingRecommendDto> rankingRecommendDtos = new ArrayList<>();
}
