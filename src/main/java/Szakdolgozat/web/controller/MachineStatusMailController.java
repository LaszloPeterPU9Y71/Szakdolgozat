package Szakdolgozat.web.controller;


import Szakdolgozat.service.EmailService;
import Szakdolgozat.web.dto.ToolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/mail")
    @RestController
    public class MachineStatusMailController {

        @Autowired
        private EmailService emailService;
        @CrossOrigin(origins = "http://localhost:4200/")
        @PostMapping("/send-email")
        public String machineStatusChanged(@RequestParam String to, @RequestParam ToolDto tool) {
            emailService.sendStatusChangedMail(to, tool);
            return "Az e-mailt sikeresen elküldtük.";
        }
    }

