package Szakdolgozat.service.mapper.entityToDto;


import Szakdolgozat.entities.ToolEntity;
import Szakdolgozat.web.dto.ToolDto;
import jakarta.annotation.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        imports = {DateTimeFormatter.class, LocalDateTime.class}

)

public interface ToolMapStructDto {

    @Mapping(target = "spareParts", expression = "java(java.util.Optional.ofNullable(toolEntity.getSpareparts()).orElse(java.util.Collections.emptyList()).stream().map(Szakdolgozat.entities.SparePartsEntity::getId).toList())")
    @Mapping(source = "isWarranty", target = "isWarranty")
    @Mapping(source = "isWarrantyTicket", target = "isWarrantyTicket")
    @Mapping(source = "isInvoice", target = "isInvoice")
    @Mapping(source = "isRegistration", target = "isRegistration")
    @Mapping(source = "identifier", target = "identifier")
    @Mapping(target = "dateOfReceiving", expression= "java(DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\").format(toolEntity.getDateOfReceiving()))")
    @Mapping(source = "ownerCompanyEmployeeEntity.name", target = "employeeName")
    @Mapping(source = "ownerCompanyEmployeeEntity.ownerCompanyEntity.companyName", target = "ownerCompanyName")
    @Mapping(source = "ownerCompanyEmployeeEntity.id", target = "employeeId")
    @Mapping(target = "defects", expression = "java(toolEntity.getDefects().stream().map(Szakdolgozat.entities.DefectEntity::getId).toList())")
    ToolDto fromEntityToDto(ToolEntity toolEntity);

    List<ToolDto> fromEntityToDtoList(Iterable<ToolEntity> toolEntities);
}
