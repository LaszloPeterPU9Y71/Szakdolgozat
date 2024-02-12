package Szakdolgozat.entities;

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

    @Column(name = "sparePartsItemName", nullable = false)
    private String partName;

    @Column(name = "sparePartsItemNumber", nullable = false, unique = true)
    private String partNumber;

    @Column(name = "sparePartsNettoBuyingPrice", nullable = false)
    private int nettoBuyingPrice;

    @Column(name = "sparePartsNettoSellingPrice", nullable = false)
    private int nettoSellingPrice;




}

