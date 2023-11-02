package com.example.Szakdolgozat.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = {"users"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"users"})
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "companyName", nullable = false)
    private String name;

    @Column(name = "comapnyPostalCode", nullable = false)
    private int postalCode;

    @Column(name = "companyTown", nullable = false)
    private String town;

    @Column(name = "companyStreet", nullable = false)
    private String street;

    @Column(name = "companyTaxNumber", nullable = false)
    private String taxNumber;

    @Column(name = "companyStatus", nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "companyEntity")
    private Set<UserEntity> users;
}


