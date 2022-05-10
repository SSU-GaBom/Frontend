package GaBom.Bom.entity;

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

    //단방향 다대1
    @ManyToOne
    @JoinColumn(name = "id")
    private Location location;

    //양방향 다대1
    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    private String images; //사진저장되는것. 고쳐야함.

    private String locationContent;




    //양방향 1대다
//    @OneToMany(mappedBy = "pin")
//    private List<Card> cardList=new ArrayList<>();

//    public void add(Card card){
//        card.setPin(this);
//        this.cardList.add(card);
//    }
}
