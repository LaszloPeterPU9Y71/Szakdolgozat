package Szakdolgozat.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SparePartsDto {
    private long id;
    private String partName;
    private String partNumber;
    private int nettoBuyingPrice;
    private int nettoSellingPrice;

}
