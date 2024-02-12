package Szakdolgozat.service.mapper;

import Szakdolgozat.web.model.CreateToolRequest;
import Szakdolgozat.entities.ToolEntity;
import org.springframework.stereotype.Component;



@Component
public class ToolMapper {

    public ToolEntity map(CreateToolRequest createToolRequest){
        return ToolEntity.builder()
                .name(createToolRequest.getName())
                .typeNumber(createToolRequest.getTypeNumber())
                .itemNumber(createToolRequest.getItemNumber())
                .serialNumber(createToolRequest.getSerialNumber())
                .build();
    }
}
