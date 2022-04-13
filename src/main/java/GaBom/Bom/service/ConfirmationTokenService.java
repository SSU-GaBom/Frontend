package GaBom.Bom.service;


import GaBom.Bom.advice.exception.CEmailAuthTokenNotFoundException;
import GaBom.Bom.entity.ConfirmationToken;
import GaBom.Bom.repository.ConfirmationTokenRepository;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;
    private final JavaMailSender javaMailSender;
    /**
     * 이메일 인증 토큰 생성
     * @return
     */
    public Boolean createEmailConfirmationToken(String userId, String receiverEmail){
        log.info("userId : {}" , userId);
        log.info("email : {}" , receiverEmail);

        Assert.hasText(userId,"userId는 필수 입니다.");
        Assert.hasText(receiverEmail,"receiverEmail은 필수 입니다.");

        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(userId);
        confirmationTokenRepository.save(emailConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("springgabom@gmail.com");
        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject("회원가입 이메일 인증");
        mailMessage.setText("http://localhost:8080/api/user/confirm-email?token="+emailConfirmationToken.getId());

        return emailService.send(mailMessage);
    }

    /**
     * 유효한 토큰 가져오기
     * @param confirmationTokenId
     * @return
     */
    public ConfirmationToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId){
        return confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(),false).orElseThrow((CEmailAuthTokenNotFoundException::new));
    };

}