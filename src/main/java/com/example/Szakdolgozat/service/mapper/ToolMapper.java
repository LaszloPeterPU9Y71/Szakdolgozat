package com.example.Szakdolgozat.service.mapper;

import com.example.Szakdolgozat.entities.ToolEntity;
import com.example.Szakdolgozat.web.model.CreateToolRequest;
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
