package Szakdolgozat.service.mapper;

import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.web.model.CreateToolRequest;
import Szakdolgozat.entities.ToolEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class ToolMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ToolEntity map(OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity, List<DefectEntity> defectEntities,
                          CreateToolRequest createToolRequest, String identifier

    ){

        String dateString = LocalDateTime.now().format(formatter);



        return ToolEntity.builder()
                .name(createToolRequest.getName())
                .typeNumber(createToolRequest.getTypeNumber())
                .itemNumber(createToolRequest.getItemNumber())
                .serialNumber(createToolRequest.getSerialNumber())
                .description(createToolRequest.getDescription())
                .dateOfReceiving(LocalDateTime.parse(dateString, formatter))
                .status("Beérkezett")
                .ownerCompanyEmployeeEntity(ownerCompanyEmployeeEntity)
                .defects(defectEntities)
                .identifier(identifier)
                .isRegistration(createToolRequest.getIsRegistration())
                .isWarranty(createToolRequest.getIsWarranty())
                .isInvoice(createToolRequest.getIsInvoice())
                .isWarrantyTicket(createToolRequest.getIsWarrantyTicket())



                .build();
    }
}
