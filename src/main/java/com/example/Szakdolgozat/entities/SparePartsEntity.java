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

public class SparePartsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sparePartsId", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "sparePartsItemNumber", nullable = false)
    private String itemNumber;

    @Column(name = "sparePartsNettoBuyingValue", nullable = false)
    private int nettoBuyingValue;

    @Column(name = "sparePartsNettoSellingValue", nullable = false)
    private int nettoSellingValue;

    //@Column(name = "sparePartsPiece", nullable = false)
    //private int pieceBuiltIn;


}

