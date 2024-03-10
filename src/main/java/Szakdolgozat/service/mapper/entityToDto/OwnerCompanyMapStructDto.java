package Szakdolgozat.service.mapper.entityToDto;

import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.web.dto.OwnerCompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )

public interface OwnerCompanyMapStructDto {

    OwnerCompanyDto fromEntityToDto(OwnerCompanyEntity ownerCompanyEntity);

    List<OwnerCompanyDto> fromEntityToDtoList(Iterable<OwnerCompanyEntity> ownerCompanyEntity);



}
