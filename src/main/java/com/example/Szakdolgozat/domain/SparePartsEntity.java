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

public class SparePartsEntity {

    @Id
    private long id;
    private String itemNumber;
    private int nettoBuyingValue;
    private int nettoSellingValue;
    private int pieceOnStock;


}

