package Szakdolgozat.service.mapper;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import org.springframework.stereotype.Component;

@Component
public class OwnerCompanyEmployeeMapper {

    public OwnerCompanyEmployeeEntity map(OwnerCompanyEntity ownerCompanyEntity, CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest){
        return OwnerCompanyEmployeeEntity.builder()
                .name(createOwnerCompanyEmployeeRequest.getName())
                .telNum(createOwnerCompanyEmployeeRequest.getTelNum())
                .email(createOwnerCompanyEmployeeRequest.getEmail())
                .title(createOwnerCompanyEmployeeRequest.getTitle())
                .status(createOwnerCompanyEmployeeRequest.isStatus())
                .ownerCompanyEntity(ownerCompanyEntity)
                .build();

    }



}
