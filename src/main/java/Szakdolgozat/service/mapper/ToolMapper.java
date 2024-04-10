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
    public ToolEntity map(OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity, List<DefectEntity> defectEntities, CreateToolRequest createToolRequest, String identifier){
        return ToolEntity.builder()
                .name(createToolRequest.getName())
                .typeNumber(createToolRequest.getTypeNumber())
                .itemNumber(createToolRequest.getItemNumber())
                .serialNumber(createToolRequest.getSerialNumber())
                .description(createToolRequest.getDescription())
                .dateOfReceiving(LocalDateTime.now())
                .status("Beérkezett")
                .ownerCompanyEmployeeEntity(ownerCompanyEmployeeEntity)
                .defects(defectEntities)
                .identifier(identifier)
                .build();
    }
}
