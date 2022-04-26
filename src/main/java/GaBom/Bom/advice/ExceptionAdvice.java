package GaBom.Bom.advice;


import GaBom.Bom.advice.exception.*;
import GaBom.Bom.model.response.CommonResult;
import GaBom.Bom.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice // 예외 발생 시 json 형태로 결과 반환 | 프로젝트의 모든 Controller에 로직 적용
// @RestControllerAdvice(basePackages = "com.example.pepega") : pepega 하위의 Controller 에만 로직 적용
public class ExceptionAdvice {

    private final ResponseService responseService; // 결과에 대한 정보를 도출하는 클래스 명시
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
        // 예외 처리 메시지를 MessageSource에서 가져오도록 수정, exception_ko.yml 파일에서 가져온 것임
        // getFailResult : setSuccess, setCode, setMsg
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(Integer.valueOf(getMessage("userNotFoundException.code")), getMessage("userNotFoundException.msg"));
        // 예외 처리 메시지를 MessageSource에서 가져오도록 수정
        // getFailResult : setSuccess, setCode, setMsg
    }

    @ExceptionHandler(CEmailAuthTokenNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult emailAuthTokenNotFoundException(HttpServletRequest request, CEmailAuthTokenNotFoundException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("emailAuthTokenNotFoundException.code")), getMessage("emailAuthTokenNotFoundException.msg"));
    }

    @ExceptionHandler(CEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult emailNotFoundException(HttpServletRequest request, CEmailNotFoundException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("emailNotFoundException.code")), getMessage("emailNotFoundException.msg"));
    }

    @ExceptionHandler(CNickNameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult nickNameAlreadyExistsException(HttpServletRequest request, CNickNameAlreadyExistsException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("nickNameAlreadyExistsException.code")), getMessage("nickNameFailedAlreadyExistsException.msg"));
    }

    @ExceptionHandler(CExtensionException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult extensionException(HttpServletRequest request, CExtensionException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("extensionException.code")), getMessage("extensionException.msg"));
    }

    @ExceptionHandler(CNoRelationException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult noRelationException(HttpServletRequest request, CNoRelationException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("noRelationException.code")), getMessage("noRelationException.msg"));
    }

    @ExceptionHandler(CRelationAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult relationAlreadyExistsException(HttpServletRequest request, CRelationAlreadyExistsException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("relationAlreadyExistsException.code")), getMessage("relationAlreadyExistsException.msg"));
    }

    @ExceptionHandler(CNotSameUserException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult notSameUserException(HttpServletRequest request, CNotSameUserException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("notSameUserException.code")), getMessage("notSameUserException.msg"));
    }

    @ExceptionHandler(CSameUserException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult sameUserException(HttpServletRequest request, CSameUserException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("sameUserException.code")), getMessage("sameUserException.msg"));
    }

    @ExceptionHandler(CUserIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userIdAlreadyExistsException(HttpServletRequest request, CUserIdAlreadyExistsException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("userIdAlreadyExistsException.code")), getMessage("userIdAlreadyExistsException.msg"));
    }

    @ExceptionHandler(CUserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userAlreadyExistsException(HttpServletRequest request, CUserAlreadyExistsException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("userAlreadyExistsException.code")), getMessage("userAlreadyExistsException.msg"));
    }

    @ExceptionHandler(CSigninFailedException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult signinFailedException(HttpServletRequest request, CSigninFailedException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("signinFailedException.code")), getMessage("signinFailedException.msg"));
    }

    @ExceptionHandler(CAuthenticationEntryPointException.class)
    public CommonResult authenticationEntryPointException(HttpServletRequest request, CAuthenticationEntryPointException e) {
        return responseService.getFailResult(Integer.valueOf(getMessage("entryPointException.code")), getMessage("entryPointException.msg"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return responseService.getFailResult((Integer.valueOf(getMessage("accessDeniedException.code"))), getMessage("accessDeniedException.msg"));
    }

    // code 정보에 해당하는 메시지를 조회한다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code 정보, 추가 argument로 현재 locale에 맞는 메시지를 조회한다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
