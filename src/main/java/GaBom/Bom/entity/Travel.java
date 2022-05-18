package GaBom.Bom.entity;


import GaBom.Bom.dto.UpdateTravelDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
//@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Travel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "travel_id")
    private Long travelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
//    @JsonIgnore //순환참조 방지용인데 다른방식으로 해야하는지 고민
    @JsonBackReference
    private User myuser;





    //TODO :: 좋아요 구현해야함. 게시물마다 이게 내가 좋아요를 눌렀는지에 대한 함수 까지
    //
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn
////    @JsonIgnore //순환참조 방지용인데 다른방식으로 해야하는지 고민.
////    @JsonBackReference
//    private User likeuser;

//    @OneToMany(mappedBy = "travel")
//    private List<User> likeusers = new ArrayList<>();

    @ManyToMany(mappedBy = "")
    @JsonIgnore
    private List<User> likeusers = new ArrayList<>();

    @ManyToMany(mappedBy = "")
    @JsonIgnore
    private List<User> zzimusers = new ArrayList<>();


//    리뷰 내부의 핀 리스트
    @OneToMany(mappedBy = "travel")
    @Column(name = "pin_list")
    @JsonManagedReference
    private List<Pin> pinList=new ArrayList<>();

    @OneToMany(mappedBy = "travel")
    @Column(name = "comment_list")
    @JsonManagedReference
    private List<Comment> commentList=new ArrayList<>();


    //리뷰 제목
    @NotNull
    private String title;

    //생성 시각
    private String appendDate;
    //수정 시각각
    private String updateDate;
    //여행 시작 날짜
    private String startDate;
    //여행 종료 날짜
    private String endDate;

//    공유 여부
    @NotNull
    private Boolean isShared;
    //좋아요 갯수
    @NotNull
    private Integer likedCount;

    @NotNull
    private Integer ZzimCount;

    //도 or 특별시
    @NotNull
    private String state;

    //시 or 군
    @NotNull
    private String city;

    //여행 경비, 본문, 교통수단
    private Integer expense;
    private String content;
    private String transportation; //TODO: enum으로 바꾸기 (?) 조회하기 쉽게



    //user에 안들어가지는것같음.

    //    연관 관계 편의 메소드
//    public void add(Pin pin){
//        pin.setTravel(this);
//        this.pinList.add(pin);
//    }


    public Travel(User user, String title, Boolean isShared, Integer likedCount, String state, String city) {
        this.myuser = user;
        this.title = title;
        this.isShared = isShared;
        this.likedCount = likedCount;
        this.state = state;
        this.city = city;
    }
    public Travel(String title, Boolean isShared, Integer likedCount, String state, String city) {
        this.title = title;
        this.isShared = isShared;
        this.likedCount = likedCount;
        this.state = state;
        this.city = city;
    }

    public static Travel CreateTravel(User user, String title, Boolean isShared, Integer likedCount, String state, String city) {
        Travel travel = new Travel();
        travel.setMyuser(user);
        travel.title = title;
        travel.isShared = isShared;
        travel.likedCount = likedCount;
        travel.state = state;
        travel.city = city;
        return travel;
    }
    //업데이트
    public void updateTravel(UpdateTravelDto updateTravelDto){
        this.title = updateTravelDto.getTitle();
        this.content = updateTravelDto.getContent();
    }

    public void add (Comment comment){
        comment.setTravel(this);
        this.commentList.add(comment);
    }
//    @PrePersist
//    public void prePersist() {
//        this.ZzimCount = this.ZzimCount == null ? 0 : this.ZzimCount;
//    }
}
