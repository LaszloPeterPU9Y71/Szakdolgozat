package Szakdolgozat.service.mapper.entityToDto;

import Szakdolgozat.entities.ToolEntity;
import Szakdolgozat.web.dto.ToolDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )
public interface ToolMapStructDto {

    @Mapping(source = "ownerCompanyEmployeeEntity.id", target = "employeeId")
    @Mapping(target = "defects", expression = "java(toolEntity.getDefects().stream().map(Szakdolgozat.entities.DefectEntity::getId).toList())")
    ToolDto fromEntityToDto(ToolEntity toolEntity);

    List<ToolDto> fromEntityToDtoList(Iterable<ToolEntity> toolEntities);
}
