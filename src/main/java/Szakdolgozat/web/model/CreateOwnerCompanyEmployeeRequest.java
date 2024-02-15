package Szakdolgozat.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter

public class CreateOwnerCompanyEmployeeRequest {

        @NotBlank(message = "A mező kitöltése kötelező")
        private String name;

        @NotBlank(message = "A mező kitöltése kötelező")
        private int telNum;

        @NotBlank(message = "A mező kitöltése kötelező")
        @Email
        private String email;

        @NotBlank(message = "A mező kitöltése kötelező")
        private String title;

        @NotBlank(message = "A mező kitöltése kötelező")
        private boolean status;

        @JsonProperty(value = "companyName")
        private String companyName;





}
