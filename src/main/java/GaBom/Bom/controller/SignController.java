package GaBom.Bom.controller;

import GaBom.Bom.advice.exception.CNickNameAlreadyExistsException;
import GaBom.Bom.advice.exception.CUserIdAlreadyExistsException;
import GaBom.Bom.dto.LoginDto;
import GaBom.Bom.dto.SignUpUserDto;
import GaBom.Bom.dto.TokenUserDto;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class SignController {

    private final ResponseService responseService; // API 요청 결과에 대한 code, message
    private final SignUpService signUpService;
    private final ConfirmationTokenService confirmationTokenService;
    private final LogInService logInService;
    private final CheckService checkService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<TokenUserDto> signin(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        return logInService.signIn(loginDto, response);
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@RequestBody SignUpUserDto signUpUserDto){
        log.info("email : {} " , signUpUserDto.getEmail());
        log.info("userId : {} " , signUpUserDto.getUserId());
        log.info("userName : {} " , signUpUserDto.getUserName());
        log.info("nickname : {} " , signUpUserDto.getNickName());
        log.info("password : {} " , signUpUserDto.getUserPw());
        //아이디, 닉네임, 이메일 중복 확인
        checkService.check(signUpUserDto);
        confirmationTokenService.createEmailConfirmationToken(signUpUserDto.getUserId(), signUpUserDto.getEmail());
        signUpService.joinUser(signUpUserDto);

        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "id중복체크")
    @GetMapping("/check-id/{userId}")
    public CommonResult checkId(@PathVariable String userId){
        log.info("checkId : {} ",userId);
        if(!checkService.checkId(userId))
            throw new CUserIdAlreadyExistsException();
        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "닉네임 중복체크")
    @GetMapping(value = "/check-nickname/{nickName}")
    public CommonResult checkNickName(@PathVariable String nickName){
        if(!checkService.checkNickName(nickName))
            throw new CNickNameAlreadyExistsException();
        return responseService.getSuccessResult();
    }
}