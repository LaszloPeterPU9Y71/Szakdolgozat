package Szakdolgozat.web.dto;


import lombok.*;

@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private long id;
    private String name;
    private String telNum;
    private String email;
    private String title;
    private Boolean status;
    private Long companyId;


}
