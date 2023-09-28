package com.example.Szakdolgozat.web.model;


import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Data
@Builder

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

}
