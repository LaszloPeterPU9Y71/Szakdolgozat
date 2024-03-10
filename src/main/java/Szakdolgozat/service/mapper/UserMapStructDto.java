package Szakdolgozat.service.mapper;

import Szakdolgozat.entities.UserEntity;
import Szakdolgozat.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring" )

public interface UserMapStructDto {
    UserDto fromEntityToDto(UserEntity userEntity);

}
