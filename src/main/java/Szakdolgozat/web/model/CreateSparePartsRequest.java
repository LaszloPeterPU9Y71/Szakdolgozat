package Szakdolgozat.web.model;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class CreateSparePartsRequest {

    @NotBlank(message = "A mező kitöltése kötelező")
    private String partName;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String partNumber;

    @NotBlank(message = "A mező kitöltése kötelező")
    private int nettoBuyingPrice;

    @NotBlank(message = "A mező kitöltése kötelező")
    private int nettoSellingPrice;


}
