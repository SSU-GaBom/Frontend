package GaBom.Bom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@Transactional
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User implements UserDetails {

    //notnull로 하면 자동 생성이 안되는 중임.
    @ApiModelProperty(value = "유저 number", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    //유저가 사용할 아이디
    //@NotNull
    @Column(name = "user_id", unique = true)
    private String userId;
    //비밀번호
    //@NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPw;
    //이메일
    //@NotNull
    @Email
    @Column(unique = true)
    private String email;
    //유저 실명
    //@NotNull
    private String userName;

    private String nickName;

    //userAuth??
    //@NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>(); // 회원이 가지고 있는 권한 정보들

    //계정 추가 시각
    //@NotNull
    private String appendDate;

    //계정 수정 시각
    //@NotNull
    private String updateDate;

    //유저 프로필이 저장될 경로
    //@NotNull
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private ProfileImage profileImage;

    private Integer followingNum;
    private Integer follwerNum;

    private String provider;
    private String refreshToken;
    private Boolean emailAuth;

    //내가 쓴 리뷰 리스트
    @OneToMany(mappedBy = "myuser")
    @Column(name = "my_travel_list")
    @JsonBackReference
    private List<Travel> myTravelList = new ArrayList<>();

    //찜을 누른 리뷰 리스트
//    // 이걸 1대 다 단방향이 엔티티측면에선 좋은데 DB에선 안좋다 해서 1대 다 양방향으로 하려는데 그게 안되서 일단 잠시 넘어감 ㅎ -- 이거
//    @OneToMany(mappedBy="likeuser")
//    @Column(name = "liked_travel_list")
//    private List<Travel> likedTravelList = new ArrayList<>();

    //-- 두번쨰
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name="travel_id")
//    private List<Travel> LikedTravelList = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="travel_id")
    @JsonIgnore
    private List<Travel> LikedTravelList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="travel_id")
    @JsonIgnore
    private List<Travel> zzimTravelList = new ArrayList<>();



//    @OneToMany(mappedBy="user")
//    private List<Travel> likedTravelList = new ArrayList<>();




    //내가 분류하여 저장한 리뷰 리스트(분류 자체로 리스트여야 하고 분류 후에도 리스트여야 해서 고민 해야함.)
//    @OneToMany
//    @JoinColumn(name = "stored_travel_id")
//    private List<StoredTravel> storedTravelList = new ArrayList<>();

    public void updatePassword(String userPw){
        this.userPw = userPw;
    }

    //이건 유저 이름 리턴하는건데 getUsername 오버라이드 된 것 때문에 오류 생겨서 getter로 안되는 듯
    public String getUserName(){
        return this.userName;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void emailVerifiedSuccess() {
        this.emailAuth = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userPw;
    }

    //이건 유일키 넘겨주는것
    @Override
    public String getUsername() {
        return this.userId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //    연관 관계 편의 메소드
    public void add(Travel travel){
        travel.setMyuser(this);
        //this.myTravelList.add(travel);
    }

    public void addLikeTravel(Travel travel){
        System.out.println("before : this.getLikedTravelList() = " + this.getLikedTravelList());
        System.out.println("travel.getLikeusers() = " + travel.getLikeusers());
        this.getLikedTravelList().add(travel);
        travel.getLikeusers().add(this);
        travel.setLikedCount(travel.getLikedCount()+1);
    }



    public void cancelLikeTravel(Travel travel) {
        if(travel.getLikedCount()<=0){
            log.info("좋아요 수가 0보다 작음. 오류. ");
        }else {
            this.getLikedTravelList().remove(travel);
//            travel.getLikeusers().add(this);
            travel.getLikeusers().remove(this);
//            travel.setLikeuser(null);
//            this.setLikedTravelList(likedTravelList1);
            travel.setLikedCount(travel.getLikedCount() - 1);
//            travel.setLikeuser(this);
        }

    }

    public void addZzimTravel(Travel travel) {
        System.out.println("before : this.getLikedTravelList() = " + this.getZzimTravelList());
        System.out.println("travel.getLikeusers() = " + travel.getZzimusers());
        this.getZzimTravelList().add(travel);
        travel.getZzimusers().add(this);
        travel.setZzimCount(travel.getZzimCount()+1);
    }

    public void cancelZzimTravel(Travel travel) {
        if(travel.getZzimCount()<=0){
            log.info("찜 수가 0보다 작음. 오류. ");
        }else {
            this.getZzimTravelList().remove(travel);
            travel.getZzimusers().remove(this);
            travel.setZzimCount(travel.getZzimCount() - 1);
        }
    }
}