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
}


