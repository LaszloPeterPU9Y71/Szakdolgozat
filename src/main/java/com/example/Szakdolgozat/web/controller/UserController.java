package com.example.Szakdolgozat.web.controller;

import com.example.Szakdolgozat.service.UserService;
import com.example.Szakdolgozat.web.dto.ResponseDto;
import com.example.Szakdolgozat.web.dto.UserDto;
import com.example.Szakdolgozat.web.model.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

   /* @PostMapping
    public UserDto createUser(@RequestBody CreateUserRequest createUserRequest) throws Exception {
        return userService.addUser(createUserRequest);
    }*/

    @GetMapping
    public ResponseDto<Set<UserDto>> getAll(){
        Set<UserDto> dummy = Collections.singleton(UserDto.builder().id(0).name("Buffy").build());
        return new ResponseDto<>(dummy);
    }


}
