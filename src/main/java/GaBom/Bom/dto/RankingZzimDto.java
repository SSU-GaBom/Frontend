package GaBom.Bom.dto;

import GaBom.Bom.entity.Travel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RankingZzimDto {
    @NotNull
    public String zzimTravelTitle;
    @NotNull
    public Long zzimTravelid;
    @NotNull
    public Integer zzimNums;

    public RankingZzimDto(Travel travel) {
        this.zzimTravelTitle = travel.getTitle();
        this.zzimTravelid = travel.getTravelId();
        this.zzimNums = travel.getZzimCount();
    }
}
