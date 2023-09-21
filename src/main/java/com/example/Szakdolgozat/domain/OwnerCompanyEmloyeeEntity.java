package com.example.Szakdolgozat.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OwnerCompanyEmloyeeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ownerCompanyEmployeeId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "ownerCompanyEmployeeName", nullable = false)
    private String name;

    @Column(name = "ownerCompanyEmployeeTelNum", nullable = false)
    private int telNum;

    @Column(name = "ownerCompanyEmployeeEmail", nullable = false)
    private String email;

    @Column(name = "ownerCompanyEmployeePassword", nullable = false)
    private String password;

    @Column(name = "ownerCompanyEmployeeTitle", nullable = false)
    private String title;

    @Column(name = "ownerCompanyEmployeeStatus", nullable = false)
    private boolean status;

}
