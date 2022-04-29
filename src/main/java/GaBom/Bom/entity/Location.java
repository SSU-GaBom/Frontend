package GaBom.Bom.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Location{

    @Id
//    @GeneratedValue
    @Column(name = "location_id")
    private Long locationId;

    //지번 주소
//    @NotNull
    private String address_name;

//    @NotNull //도로명주소
    private String road_address_name;

    private String place_name;

    //혹시 이것때문인가 하고 적어봄 ?? 왜 단방향이면 안되고 양방향 하면 되는것?
//    @OneToMany(mappedBy = "location")
//    @Column(name = "location_list")
//    private List<Pin> locationList=new ArrayList<>();

    @NotNull
    private Float latitude; //x값

    //경도
    @NotNull
    private Float longitude;//y값

    //이 두개는 보류
    private Integer city;
    private Integer state;
}
