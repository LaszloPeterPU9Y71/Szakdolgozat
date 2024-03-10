package Szakdolgozat.web.controller;

import Szakdolgozat.service.UserService;
import Szakdolgozat.web.dto.UserDto;
import Szakdolgozat.web.model.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor


public class UserController {


    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        UserDto userDto = userService.addUser(createUserRequest);
        return ResponseEntity.ok(userDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        List<UserDto> userDto = userService.findAllUsers();
        return ResponseEntity.ok(userDto);
    }

    
    @DeleteMapping("/delete/{id}")
    public String softDelete(@PathVariable("id") long id) {
        userService.softDelete(id);
        return "A: " + id + " azonosítójú felhasználót törlésre került";
    }

    @PutMapping("/update/{id}")
    public String updateUserData(@PathVariable("id") long id,
                               @RequestBody CreateUserRequest createUserRequest) {
            userService.updateUserPersonalData(id, createUserRequest);
        return "A felhasználó adatai frissültek!";
    }

    @PutMapping("/pw/{id}")
    public String updateUserPassword(@PathVariable("id") long id,
                                    @RequestBody CreateUserRequest createUserRequest) {
        userService.updateUserPassword(id, createUserRequest);
        return "A felhasználó jelszava frissült";
    }
}





