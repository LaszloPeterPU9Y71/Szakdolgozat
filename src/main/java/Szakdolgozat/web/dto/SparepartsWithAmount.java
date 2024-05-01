package Szakdolgozat.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SparepartsWithAmount {

    private Long id;
    private SparePartsDto spareParts;
    private Long amount;


}
