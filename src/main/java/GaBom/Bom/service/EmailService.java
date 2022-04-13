package GaBom.Bom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public Boolean send(SimpleMailMessage email) {
        try {
            javaMailSender.send(email);
        }catch (MailException e){
            return false;
        }
        return true;
    }
}