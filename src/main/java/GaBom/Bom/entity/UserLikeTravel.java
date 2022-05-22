package GaBom.Bom.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserLikeTravel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLikeTravelid;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

}
