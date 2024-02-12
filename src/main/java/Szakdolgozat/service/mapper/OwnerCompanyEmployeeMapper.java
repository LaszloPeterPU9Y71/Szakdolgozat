package Szakdolgozat.service.mapper;

import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import org.springframework.stereotype.Component;

@Component
public class OwnerCompanyEmployeeMapper {

    public OwnerCompanyEmloyeeEntity map(CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest){
        return OwnerCompanyEmloyeeEntity.builder()
                .name(createOwnerCompanyEmployeeRequest.getName())
                .telNum(createOwnerCompanyEmployeeRequest.getTelNum())
                .email(createOwnerCompanyEmployeeRequest.getEmail())
                .title(createOwnerCompanyEmployeeRequest.getTitle())
                .status(createOwnerCompanyEmployeeRequest.isStatus())
                .build();

    }



}
