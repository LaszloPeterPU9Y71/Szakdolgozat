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
    @Column(name = "spare_parts_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private long id;

    @Column(name = "spare_parts_item_name", nullable = false)
    private String partName;

    @Column(name = "spare_parts_item_number", nullable = false, unique = true)
    private String partNumber;

    @Column(name = "spare_parts_netto_buying_price", nullable = false)
    private int nettoBuyingPrice;

    @Column(name = "spare_parts_netto_selling_price", nullable = false)
    private int nettoSellingPrice;




}

