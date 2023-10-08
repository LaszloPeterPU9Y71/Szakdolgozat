package com.example.Szakdolgozat.service.mapper;

import com.example.Szakdolgozat.entities.OwnerCompanyEntity;
import com.example.Szakdolgozat.web.model.CreateOwnerCompanyRequest;
import org.springframework.stereotype.Component;

@Component
public class OwnerCompanyMapper {

    public static OwnerCompanyEntity map(CreateOwnerCompanyRequest createOwnerCompanyRequest){
        return OwnerCompanyEntity.builder()
                .name(createOwnerCompanyRequest.getName())
                .postalCode(createOwnerCompanyRequest.getPostalCode())
                .town(createOwnerCompanyRequest.getTown())
                .street(createOwnerCompanyRequest.getStreet())
                .taxNumber(createOwnerCompanyRequest.getTaxNumber())
                .accountNumber(createOwnerCompanyRequest.getAccountNumber())
                .status(createOwnerCompanyRequest.isStatus())
                .build();
    }


}
