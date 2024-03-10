package Szakdolgozat.service.mapper;
import Szakdolgozat.web.model.CreateDefectRequest;
import Szakdolgozat.entities.DefectEntity;
import org.springframework.stereotype.Component;

@Component
public class DefectMapper {

    public DefectEntity map(CreateDefectRequest createDefectRequest){
        return DefectEntity.builder()
                .name(createDefectRequest.getName())
                .description(createDefectRequest.getDescription())
                .build();
    }


}
