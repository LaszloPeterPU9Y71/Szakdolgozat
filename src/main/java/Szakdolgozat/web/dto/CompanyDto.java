package Szakdolgozat.web.dto;


import Szakdolgozat.entities.CompanyEntity;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CompanyDto {

    private long id;
    private String name;
    private int postalCode;
    private String town;
    private String street;
    private String taxNumber;
    private Boolean status;

}
