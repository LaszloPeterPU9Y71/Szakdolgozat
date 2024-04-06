package Szakdolgozat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private final JavaMailSender mailSender;


    @Value("${spring.mail.properties.from}")
    private String from;
    public void sendStatusChangedMail(String to, String machineName){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("szerviz@szerszamkucko.hu");
        message.setTo(to);
        message.setSubject("A javításra beadott gép státusza megváltozott");
        message.setText("A javításra beadott" + machineName + " státusza megváltozott, kollégánk hamarosan felveszi Önnel a kapcsolatot!");
        mailSender.send(message);
    }
}
