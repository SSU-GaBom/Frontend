package GaBom.Bom.test;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class Test {

    @Id
    @GeneratedValue
    Long id;

    private String loginId;
    private String password;
    private String email;
    private String name;
    private String nickName;


}
