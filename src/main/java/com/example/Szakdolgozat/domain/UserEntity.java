package com.example.Szakdolgozat.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {

    @Id
    private long id;
    private String name;
    private int telNum;
    private String email;
    private String password;
    private String title;
    private boolean status;
    // If state is true, the user is available, if false, user is soft deleted!

}
