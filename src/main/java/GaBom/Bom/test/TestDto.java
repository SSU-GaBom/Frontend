package GaBom.Bom.test;

import lombok.Getter;

@Getter
public class TestDto {

    private String loginId;
    private String password;
    private String email;
    private String name;
    private String nickName;

    public Test toEntity(){
        return Test.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .name(name)
                .nickName(nickName)
                .build();
    }
}
