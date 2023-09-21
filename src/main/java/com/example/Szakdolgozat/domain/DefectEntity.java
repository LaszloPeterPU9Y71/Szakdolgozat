package com.example.Szakdolgozat.domain;

import jakarta.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "defectId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "defectName", nullable = false)
    private String name;

}
