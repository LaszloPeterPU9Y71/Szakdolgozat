package Szakdolgozat.service.mapper.entityToDto;

import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.web.dto.CompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )
public interface CompanyMapStructDto {

    CompanyDto fromEntityToDto(CompanyEntity companyEntity);

}
