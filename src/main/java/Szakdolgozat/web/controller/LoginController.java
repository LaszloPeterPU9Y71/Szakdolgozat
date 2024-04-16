package Szakdolgozat.web.controller;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import Szakdolgozat.entities.UserEntity;
import Szakdolgozat.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(HttpServletRequest httpServletRequest) {

        String upd = httpServletRequest.getHeader("authorization");
        String pair = new String(Base64.decodeBase64(upd.substring(6)));
        String email = pair.split(":")[0];
        String password = pair.split(":")[1];

        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            throw new DataNotFoundException();
        }
        var x = userEntity.get();


        if (!bCryptPasswordEncoder.matches(password,x.getPassword())) {
            throw new RuntimeException();
        }

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "ok");

        return ResponseEntity.ok(responseBody);

    }

}
