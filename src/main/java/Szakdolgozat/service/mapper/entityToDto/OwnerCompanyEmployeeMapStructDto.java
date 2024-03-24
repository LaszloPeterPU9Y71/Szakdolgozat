package Szakdolgozat.service.mapper.entityToDto;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.web.dto.OwnerCompanyDto;
import Szakdolgozat.web.dto.OwnerCompanyEmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )
public interface OwnerCompanyEmployeeMapStructDto {
    @Mapping(source = "ownerCompanyEntity.id", target = "ownerCompanyId")
    OwnerCompanyEmployeeDto fromEntityToDto(OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity);

    List<OwnerCompanyEmployeeDto> fromEntityToDtoList(Iterable<OwnerCompanyEmployeeEntity> ownerCompanyEmployeeEntities);
}
