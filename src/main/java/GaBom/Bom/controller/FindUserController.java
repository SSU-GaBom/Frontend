package GaBom.Bom.controller;


import GaBom.Bom.dto.FindUserDto;
import GaBom.Bom.dto.UpdatePasswordDto;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.service.FindUserService;
import GaBom.Bom.service.ResponseService;
import GaBom.Bom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    //성공하면 db에 유저별로 난수(유효시간 5분)가 저장 되고 이메일이 전송됨.
    @PostMapping("/send-randomint")
    public CommonResult sendRandomInt(@RequestBody FindUserDto findUserDto){
        return findUserService.sendRandomInt(findUserDto);
    }

    //인증이 완료되면 "일치합니다." 라는 값이 data에 담겨 넘어감.
    @PostMapping("/authentication-randomint")
    public CommonResult authenticationRandomInt(@RequestParam String randomInt,
                                                @RequestBody FindUserDto findUserDto){
        return findUserService.authenticationRandomInt(randomInt, findUserDto);
    }

    @PostMapping("/update-pw")
    public CommonResult changePw(@RequestBody UpdatePasswordDto updatePasswordDto){
        findUserService.updatePassword(updatePasswordDto);

        return responseService.getSuccessResult();
    }
}
