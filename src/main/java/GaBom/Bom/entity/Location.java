package GaBom.Bom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "id")
    private Long Id;

    //지번 주소
//    @NotNull
    private String address_name; // 지번주소
    private String category_group_code;
    private String category_group_name;
    private String category_name;

    private String distance;
    private String phone;
    private String place_name;
    private String place_url;
    private String road_address_name; // 도로명주소



    //혹시 이것때문인가 하고 적어봄 ?? 왜 단방향이면 안되고 양방향 하면 되는것?
//    @OneToMany(mappedBy = "location")
//    @Column(name = "location_list")
//    @JsonBackReference
//    private List<Pin> locationList=new ArrayList<>();

    @NotNull
    private Float x; //x값

    //경도
    @NotNull
    private Float y;//y값

}
