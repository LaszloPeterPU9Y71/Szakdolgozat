package Szakdolgozat.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data

@Getter
@Setter

public class CreateDefectRequest {

    @NotBlank(message = "A mező kitöltése kötelező")
    private String name;


}
