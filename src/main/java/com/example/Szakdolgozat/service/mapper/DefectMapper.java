package com.example.Szakdolgozat.service.mapper;
import com.example.Szakdolgozat.entities.DefectEntity;
import com.example.Szakdolgozat.web.model.CreateDefectRequest;
import org.springframework.stereotype.Component;

@Component
public class DefectMapper {

    public DefectEntity map(CreateDefectRequest createDefectRequest){
        return DefectEntity.builder()
                .name(createDefectRequest.getName())
                .build();
    }


}
