package GaBom.Bom.controller;

import GaBom.Bom.advice.exception.CEmailNotFoundException;
import GaBom.Bom.advice.exception.CNickNameAlreadyExistsException;
import GaBom.Bom.advice.exception.CUserIdAlreadyExistsException;
import GaBom.Bom.dto.LoginDto;
import GaBom.Bom.dto.SignUpUserDto;
import GaBom.Bom.dto.TokenUserIdDto;
import GaBom.Bom.dto.UserDto;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.model.response.SingleResult;
import GaBom.Bom.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Slf4j
@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
public class SignController {

    private final ResponseService responseService; // API 요청 결과에 대한 code, message
    private final SignUpService signUpService;
    private final ConfirmationTokenService confirmationTokenService;
    private final LogInService logInService;
    private final CheckService checkService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/api/signin")
    public SingleResult<TokenUserIdDto> signin(@RequestBody LoginDto loginDto) {
        return logInService.signIn(loginDto);
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/api/signup")
    public CommonResult signup(@RequestBody SignUpUserDto signUpUserDto) throws MessagingException {
        log.info("email : {} " , signUpUserDto.getEmail());
        log.info("userId : {} " , signUpUserDto.getUserId());
        log.info("userName : {} " , signUpUserDto.getUserName());
        log.info("nickname : {} " , signUpUserDto.getNickName());
        log.info("password : {} " , signUpUserDto.getUserPw());
        //아이디, 닉네임, 이메일 중복 확인
        checkService.check(signUpUserDto);
        if(!confirmationTokenService.createEmailConfirmationToken(signUpUserDto.getUserId(), signUpUserDto.getEmail()))
            throw new CEmailNotFoundException();
        signUpService.joinUser(signUpUserDto);
        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "id중복체크")
    @GetMapping("/api/checkId/{userId}")
    public CommonResult checkId(@PathVariable String userId){
        log.info("checkId : {} ",userId);
        if(!checkService.checkId(userId))
            throw new CUserIdAlreadyExistsException();
        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "닉네임 중복체크")
    @GetMapping(value = "/api/checkNickname/{nickName}")
    public CommonResult checkNickName(@PathVariable String nickName){
        if(!checkService.checkNickName(nickName))
            throw new CNickNameAlreadyExistsException();
        return responseService.getSuccessResult();
    }
}