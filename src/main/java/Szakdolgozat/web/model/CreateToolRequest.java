package Szakdolgozat.web.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Data


public class CreateToolRequest {

    @NotBlank(message = "A mező kitöltése kötelező")
    private String name;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String typeNumber;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String itemNumber;

    @NotBlank(message = "A mező kitöltése kötelező")
    
    private String serialNumber;

    @NotBlank(message = "A mező kitöltése kötelező")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private LocalDateTime dateOfReceiving;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String status;

    @JsonProperty(value= "ownerCompanyEmployee")
    private long ownerCompanyEmployeeId;

    @JsonProperty(value= "defectsId")
    private long defectsId;

}
