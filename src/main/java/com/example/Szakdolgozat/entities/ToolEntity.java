package com.example.Szakdolgozat.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class ToolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toolId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "toolName", nullable = false)
    private String name;

    @Column(name = "toolTypeNumber", nullable = false)
    private String typeNumber;

    @Column(name = "toolItemNumber", nullable = false)
    private String itemNumber;

    @Column(name = "toolSerialNumber", nullable = false)
    private String serialNumber;

    @Column(name = "toolDateOfRecieving", nullable = true)
  //  @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date dateOfReceiving;


}
