package Szakdolgozat.web.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data


public class CreateToolRequest {


    private String name;

    private String typeNumber;

    private String itemNumber;

    private String serialNumber;

    private String dateOfReceiving;

    private String status;

    private String description;

    private long employeeId;

    private List<Long> defects;

    private String identifier;

    private boolean isWarranty;

    private boolean isWarrantyTicket;

    private boolean isInvoice;

    private boolean isRegistration;







}
