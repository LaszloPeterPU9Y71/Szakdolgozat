package Szakdolgozat.service.mapper;

import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.web.model.CreateCompanyRequest;

public class CompanyMapper {
    public static CompanyEntity map(CreateCompanyRequest createCompanyRequest){
        return CompanyEntity.builder()
                .name(createCompanyRequest.getName())
                .postalCode(createCompanyRequest.getPostalCode())
                .town(createCompanyRequest.getTown())
                .street(createCompanyRequest.getStreet())
                .taxNumber(createCompanyRequest.getTaxNumber())
                .build();
    }

}
