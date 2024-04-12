package Szakdolgozat.web.model;

import Szakdolgozat.entities.DefectEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

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
    private String dateOfReceiving;

    @NotBlank(message = "A mező kitöltése kötelező")
    private String status;

    private String description;

    private long employeeId;

    private List<Long> defects;

    private String identifier;







}
