package com.example.Szakdolgozat.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "user_entity")

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "UserName", nullable = false)
    private String name;

    @Column(name = "UserTelNum", nullable = false)
    private int telNum;

    @Column(name = "UserEmail", nullable = false, unique = true)
    private String email;

    @Column(name = "UserPassword", nullable = false)
    private String password;

    @Column(name = "UserTitle", nullable = false)
    private String title;

    @Column(name = "UserStatus", nullable = false)
    private boolean status;


    // If state is true, the user is available, if false, user is soft deleted.
}
