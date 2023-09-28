package com.example.Szakdolgozat.web.model;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
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
