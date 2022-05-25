package GaBom.Bom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserTravelDto {
    private Long travelId;

    private List<String> Images;
}
