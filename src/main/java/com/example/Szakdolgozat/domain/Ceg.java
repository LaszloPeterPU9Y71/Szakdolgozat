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
public class Ceg {

    @Id
    private long id;
    private String company;
    private int postalCode;
    private String town;
    private String street;
    private String taxNumber;
    private Boolean status;
}


