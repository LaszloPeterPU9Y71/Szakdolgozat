package Szakdolgozat.service.mapper;

import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.web.model.CreateToolRequest;
import Szakdolgozat.entities.ToolEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


@Component
public class ToolMapper {

    public ToolEntity map(OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity, DefectEntity defectEntity, CreateToolRequest createToolRequest){
        return ToolEntity.builder()
                .name(createToolRequest.getName())
                .typeNumber(createToolRequest.getTypeNumber())
                .itemNumber(createToolRequest.getItemNumber())
                .serialNumber(createToolRequest.getSerialNumber())
                .description(createToolRequest.getDescription())
                .dateOfReceiving(LocalDateTime.now())
                .status("Beérkezett")
                .ownerCompanyEmployeeEntity(ownerCompanyEmployeeEntity)
                .defects(List.of(defectEntity))
                .build();
    }
}
