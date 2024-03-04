package Szakdolgozat.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OwnerCompanyDto {
    private long id;
    private String companyName;
    private int postalCode;
    private String town;
    private String street;
    private String taxNumber;
    private String accountNumber;
    private Boolean status;
}
