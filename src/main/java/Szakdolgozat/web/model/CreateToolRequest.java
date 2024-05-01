package Szakdolgozat.web.model;

import Szakdolgozat.web.dto.SparepartsWithAmount;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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

    private Boolean isWarranty;

    private Boolean isWarrantyTicket;

    private Boolean isInvoice;

    private Boolean isRegistration;

    private Map<Long, Integer> sparePartsMap;

    private List<SparepartsWithAmount> sparepartlist;






}
