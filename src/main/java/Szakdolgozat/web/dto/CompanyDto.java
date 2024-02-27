package Szakdolgozat.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
