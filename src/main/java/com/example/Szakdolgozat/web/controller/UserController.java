package com.example.Szakdolgozat.web.controller;

import com.example.Szakdolgozat.entities.UserEntity;
import com.example.Szakdolgozat.repository.UserRepository;
import com.example.Szakdolgozat.service.UserService;
import com.example.Szakdolgozat.web.model.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor


public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        return userService.addUser(createUserRequest);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<UserEntity> findAllUsers(){
        return userRepository.findAll();
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
        return "User Password updated";
    }
}





