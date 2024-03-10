package Szakdolgozat.web.controller;

import Szakdolgozat.entities.UserEntity;
import Szakdolgozat.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        UserDto userDto = userService.addUser(createUserRequest);
        return ResponseEntity.ok(userDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<UserDto>> findAllUsers(){
        List<UserDto> userDto = userService.findAllUsers();
        return ResponseEntity.ok(userDto);
    }

    
    @DeleteMapping("/delete/{id}")
    public String softDelete(@PathVariable("id") long id) {
        userService.softDelete(id);
        return "The user with id: " + id + " has been deleted";
    }

    @PutMapping("/update/{id}")
    public String updateUserData(@PathVariable("id") long id,
                               @RequestBody CreateUserRequest createUserRequest) {
            userService.updateUserPersonalData(id, createUserRequest);
        return "User data updated!";
    }

    @PutMapping("/pw/{id}")
    public String updateUserPassword(@PathVariable("id") long id,
                                    @RequestBody CreateUserRequest createUserRequest) {
        userService.updateUserPassword(id, createUserRequest);
        return "User password updated";
    }
}





