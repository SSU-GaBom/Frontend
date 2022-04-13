package GaBom.Bom.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserAuthDto {
    private String email;
    private String password;
}
