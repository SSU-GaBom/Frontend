package GaBom.Bom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Pin{

    @Id
    @Column(name = "pin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Pin의 번호는 "트레블 ID" + "인덱스"
    private Long pinId;

    //pin <-> location : 다 대 1 단방향
    //양방향으로 바꿀까..?
    @ManyToOne(cascade = CascadeType.MERGE) //MERGE  이유 : location과 pin을 따로따로 저장해야해서
    @JoinColumn(name = "id")
//    @JsonManagedReference
    private Location location;

    //pin과 travel  : 다 : 1양방향 다대1
    @ManyToOne
    @JoinColumn(name = "travel_id")
    @JsonBackReference
    private Travel travel;



    //pin 과 location : 1 대 다
    @OneToMany(mappedBy = "pin" , cascade = CascadeType.ALL)
    @Column(name="images")
    @JsonManagedReference
    private List<TravelImage> images = new ArrayList<>();


//    @OneToMany(mappedBy="pin") //사진들
//    @Column(name = "travel_images")
//    @JsonIgnore
//    private List<TravelImage> travelImages;

    private String locationContent;

    public void setTravelImage(TravelImage image){
        image.setPin(this);
//        this.images.add(image);
    }

}
