package GaBom.Bom.controller;


import GaBom.Bom.advice.exception.CCheckIdFailedException;
import GaBom.Bom.advice.exception.CUserEmailAlreadyExistsException;
import GaBom.Bom.dto.LoginDto;
import GaBom.Bom.dto.UserDto;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.service.ConfirmationTokenService;
import GaBom.Bom.service.LogInService;
import GaBom.Bom.service.ResponseService;
import GaBom.Bom.service.SignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/sign")
public class SignController {

    private final ResponseService responseService; // API 요청 결과에 대한 code, message
    private final SignUpService signUpService;
    private final ConfirmationTokenService confirmationTokenService;
    private final LogInService logInService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@RequestBody LoginDto loginDto) {
        return logInService.signIn(loginDto);
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@RequestBody UserDto userDto) throws MessagingException {
        if(!signUpService.checkEmail(userDto.getEmail())){
            throw new CUserEmailAlreadyExistsException();
        }
        confirmationTokenService.createEmailConfirmationToken(userDto.getUserId(), userDto.getEmail());
        signUpService.joinUser(userDto);
        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "id중복체크")
    @PostMapping(value = "/checkid")
    public CommonResult checkId(@RequestParam HashMap<String, String> userId){
        if(!signUpService.checkId(userId.get("userId")))
            throw new CCheckIdFailedException();
        return responseService.getSuccessResult();
    }
}