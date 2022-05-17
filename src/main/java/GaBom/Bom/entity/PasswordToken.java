package GaBom.Bom.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordToken {
    
    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;	//토큰 만료 시간

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime expirationDate;

    @Column
    private boolean expired;

    //일부러 FK 사용 안함
    @Column
    private Long userNo;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    private String randomInt;

    public void setExpired(Boolean expired){
        this.expired = expired;
    }

    /**
     * 이메일 인증 토큰 생성
     * @param userNo
     * @return
     */
    public static PasswordToken createPasswordToken(Long userNo, String randomInt){
        PasswordToken passwordToken = new PasswordToken();
        passwordToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분후 만료
        passwordToken.userNo = userNo;
        passwordToken.expired = false;
        passwordToken.randomInt = randomInt;
        return passwordToken;
    }

    public static void updatePasswordToken(PasswordToken passwordToken, String randomInt){
        passwordToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분후 만료
        passwordToken.expired = false;
        passwordToken.randomInt = randomInt;

    }

    /**
     * 토큰 사용으로 인한 만료
     */
    public void useToken(){
        expired = true;
    }
}
