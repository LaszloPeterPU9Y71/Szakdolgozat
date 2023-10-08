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

public class CreateOwnerCompanyRequest {


    @NotBlank(message = "A mező kitöltése kötelező")
    private String name;

    @NotBlank(message = "A mező kitöltése kötelező")
    private int postalCode;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String town;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String street;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String taxNumber;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String accountNumber;

    @NotBlank(message = "A mező kitöltése kötelező")
    private boolean status;
}

