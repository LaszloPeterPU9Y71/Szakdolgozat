package Szakdolgozat.service.mapper.entityToDto;

import Szakdolgozat.entities.ToolEntity;
import Szakdolgozat.web.dto.ToolDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )
public interface ToolMapStructDto {
    ToolDto fromEntityToDto(ToolEntity toolEntity);

    List<ToolDto> fromEntityToDtoList(Iterable<ToolEntity> toolEntities);
}
