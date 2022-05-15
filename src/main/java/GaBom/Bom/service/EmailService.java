package GaBom.Bom.service;

import GaBom.Bom.entity.ConfirmationToken;
import GaBom.Bom.repository.ConfirmationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Async
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void pageAuth(String toEmail, String userId, String uri){
        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(userId);
        confirmationTokenRepository.save(emailConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("springgabom@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("인증 메일입니다.");
        mailMessage.setText(uri+emailConfirmationToken.getId());

        send(mailMessage);
    }

    public void intAuth(String toEmail,String randomIntString){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("springgabom@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setSubject("인증 메일입니다.");
        mailMessage.setText(randomIntString + "해당 인증번호를 입력하세요.");

        send(mailMessage);
    }

    public Boolean send(SimpleMailMessage email) {
        try {
            javaMailSender.send(email);
        }catch (MailException e){
            return false;
        }
        return true;
    }
}