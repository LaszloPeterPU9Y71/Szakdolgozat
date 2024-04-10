package Szakdolgozat.service.mapper.entityToDto;

import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.web.dto.DefectDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )

public interface DefectMapStructDto {


    DefectDto fromEntityToDto(DefectEntity defectEntity);

    List<DefectDto> fromEntityToDtoList(Iterable<DefectEntity> defectEntities);

}
