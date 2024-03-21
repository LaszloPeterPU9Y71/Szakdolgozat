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
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "name", nullable = false)
    private String partName;

    @Column(name = "number", nullable = false, unique = true)
    private String partNumber;

    @Column(name = "netto_buying_price", nullable = false)
    private int nettoBuyingPrice;

    @Column(name = "netto_selling_price", nullable = false)
    private int nettoSellingPrice;




}

