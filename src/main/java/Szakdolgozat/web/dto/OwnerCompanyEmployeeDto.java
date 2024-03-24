package Szakdolgozat.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OwnerCompanyEmployeeDto {
    private long id;
    private String name;
    private String telNum;
    private String email;
    private String title;
    private Boolean status;
    private long ownerCompanyId;
}
