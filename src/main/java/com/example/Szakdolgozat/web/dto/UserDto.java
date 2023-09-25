package com.example.Szakdolgozat.web.dto;


import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter

public class UserDto {


    private long id;
    private String name;
    private int telNum;
    private String email;
    private String password;
    private String title;
    private boolean status;


}
