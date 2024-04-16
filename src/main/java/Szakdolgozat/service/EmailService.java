package Szakdolgozat.service;

import Szakdolgozat.web.dto.ToolDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {


    private final JavaMailSender mailSender;


    @Value("${spring.mail.properties.from}")
    private String from;
    public void sendStatusChangedMail(String to, ToolDto tool){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("A javításra beadott gép státusza megváltozott");
        message.setText("A javításra beadott " + tool.getName() + " státusza megváltozott, kollégánk hamarosan felveszi Önnel a kapcsolatot!");
        mailSender.send(message);
    }
}
