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

public class OwnerCompanyEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ownerCompanyId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "ownerCompanyName", nullable = false)
    private String name;

    @Column(name = "ownerCompanyPostalCode", nullable = false)
    private int postalCode;

    @Column(name = "ownerCompanyTown", nullable = false)
    private String town;

    @Column(name = "ownerCompanyStreet", nullable = false)
    private String street;

    @Column(name = "ownerCompanyTaxNumber", nullable = false)
    private String taxNumber;

    @Column(name = "ownerCompanyAccountNumber", nullable = true)
    private String accountNumber;

    @Column(name = "ownerCompanyStatus", nullable = false)
    private Boolean status;


}
