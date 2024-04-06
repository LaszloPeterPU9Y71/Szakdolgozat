package Szakdolgozat.web.controller;


import Szakdolgozat.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RequestMapping("api/v1/mail")
    @RestController
    public class MachineStatusMailController {

        @Autowired
        private EmailService emailService;

        @PostMapping("/send-email")
        public String machineStatusChanged(@RequestParam String to, @RequestParam String machineName) {
            emailService.sendStatusChangedMail(to, machineName);
            return "Email sent successfully.";
        }
    }

