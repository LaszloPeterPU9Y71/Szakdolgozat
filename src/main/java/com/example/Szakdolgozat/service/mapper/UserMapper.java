package com.example.Szakdolgozat.service.mapper;

import com.example.Szakdolgozat.domain.UserEntity;
import com.example.Szakdolgozat.web.model.CreateUserRequest;
import org.springframework.stereotype.Component;



@Component
public class UserMapper {

    public UserEntity map(CreateUserRequest createUserRequest){
        return UserEntity.builder()
                .name(createUserRequest.getName())
                .telNum(createUserRequest.getTelNum())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .title(createUserRequest.getTitle())
                .status(createUserRequest.isStatus())
                .build();
    }
}
