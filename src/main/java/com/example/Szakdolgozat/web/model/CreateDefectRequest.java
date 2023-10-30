package com.example.Szakdolgozat.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
@Builder
@Getter
@Setter

public class CreateDefectRequest {

    @NotBlank(message = "A mező kitöltése kötelező")
    private String name;


}
