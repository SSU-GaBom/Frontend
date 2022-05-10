package GaBom.Bom.controller;


import GaBom.Bom.dto.FindUserDto;
import GaBom.Bom.dto.UserAuthDto;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.service.FindUserService;
import GaBom.Bom.service.ResponseService;
import GaBom.Bom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FindUserController {

    private final FindUserService findUserService;
    private final ResponseService responseService;
    private final UserService userService;

    //이메일과 이름을 넘기면 난수를 생성하고 이메일칸, 이름 칸은 비활성한다. 그리고 인증번호를 치는 창이 활성화 된다. 정상적으로 생성 됐는지 true false로 결과 값 출력
    //boolean type return임.
    @GetMapping("/find-id")
    public SingleResult<String> isUserIdExist(@RequestBody FindUserDto finduserDto) {
        return responseService.getSingleResult(findUserService.findId(finduserDto));
    }

    //이메일과 아이디를 넘기면 난수를 생성하고 이메일칸, 아이디 칸은 비활성한다. 그리고 인증번호 치는 창이 활성화 된다. 정상적으로 생성 됐는지 true false로 결과 값 출력
    //boolean type return 임.
    @PostMapping("/find-pw")
    public CommonResult isUserPwExist(@RequestBody FindUserDto findUserDto) {
        return findUserService.findPassword(findUserDto);
    }

    //프론트에서 확인 비밀번호와 그냥 비밀번호가 같다면 이메일과 함께 -> 여긴 고쳐야함.
    @PostMapping("/change-pw")
    public void changePw(@RequestParam String token, @RequestBody UserAuthDto userAuthDto, HttpServletResponse response) throws IOException {
        userService.confirmEmailChangePw(token, userAuthDto);
        String redirect_uri="http://localhost:8081/api/sign/sginin";
        response.sendRedirect(redirect_uri);
    }
}
