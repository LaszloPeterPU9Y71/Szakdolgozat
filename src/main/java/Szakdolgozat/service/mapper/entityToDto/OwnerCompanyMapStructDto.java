package Szakdolgozat.service.mapper.entityToDto;

import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.web.dto.OwnerCompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )

public interface OwnerCompanyMapStructDto {

    OwnerCompanyDto fromEntityToDto(OwnerCompanyEntity ownerCompanyEntity);

}
