package com.example.Szakdolgozat.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class DefectEntity {

    @Id
    private long id;
    private String name;

}
