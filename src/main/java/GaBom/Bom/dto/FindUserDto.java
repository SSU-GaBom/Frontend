package GaBom.Bom.dto;

import lombok.Data;

@Data
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FindUserDto {

    //id 찾을 때만 쓰는 요소들
    private String email;
    private String userName;

    //pw 찾을 때 쓰는 요소
    private String userId;
}
