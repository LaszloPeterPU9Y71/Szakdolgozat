package com.example.Szakdolgozat.service.mapper;

import com.example.Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import com.example.Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import com.example.Szakdolgozat.web.model.CreateOwnerCompanyRequest;
import org.springframework.stereotype.Component;

@Component
public class OwnerCompanyEmployeeMapper {

    public static OwnerCompanyEmloyeeEntity map(CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest){
        return OwnerCompanyEmloyeeEntity.builder()
                .name(createOwnerCompanyEmployeeRequest.getName())
                .telNum(createOwnerCompanyEmployeeRequest.getTelNum())
                .email(createOwnerCompanyEmployeeRequest.getEmail())
                .password(createOwnerCompanyEmployeeRequest.getPassword())
                .title(createOwnerCompanyEmployeeRequest.getTitle())
                .status(createOwnerCompanyEmployeeRequest.isStatus())
                .build();

    }



}
