package com.example.Szakdolgozat.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(name = "ownerCompanyEmployeeEmail", nullable = false, unique = true)
    private String email;

    @Column(name = "ownerCompanyEmployeePassword", nullable = false)
    private String password;

    @Column(name = "ownerCompanyEmployeeTitle", nullable = false)
    private String title;

    @Column(name = "ownerCompanyEmployeeStatus", nullable = false)
    private boolean status;


    @ManyToOne(optional = false)
    @JoinColumn(name = "OwnerCompanyEmployee",
            foreignKey = @ForeignKey(name = "FK_OwnerCompany_Employee"))
    private OwnerCompanyEntity ownerCompanyEntity;

    @OneToMany(mappedBy="ownerCompanyEmloyeeEntity")
    private List<ToolEntity> tools;


}
