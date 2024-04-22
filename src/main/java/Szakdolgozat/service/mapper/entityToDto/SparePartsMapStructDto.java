package Szakdolgozat.service.mapper.entityToDto;

import Szakdolgozat.entities.SparePartsEntity;
import Szakdolgozat.entities.UserEntity;
import Szakdolgozat.web.dto.SparePartsDto;
import Szakdolgozat.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )
public interface SparePartsMapStructDto {

    SparePartsDto fromEntityToDto(SparePartsEntity sparePartsEntity);

    SparePartsDto fromEntityToDtoOptional(Optional<SparePartsEntity> sparePartsEntity);

    List<SparePartsDto> fromEntitytoDtoList(Iterable<SparePartsEntity> sparePartsEntities);



}
