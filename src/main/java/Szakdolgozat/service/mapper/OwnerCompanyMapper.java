package Szakdolgozat.service.mapper;

import Szakdolgozat.web.model.CreateOwnerCompanyRequest;
import Szakdolgozat.entities.OwnerCompanyEntity;
import org.springframework.stereotype.Component;

@Component
public class OwnerCompanyMapper {

    public static OwnerCompanyEntity map(CreateOwnerCompanyRequest createOwnerCompanyRequest){
        return OwnerCompanyEntity.builder()
                .companyName(createOwnerCompanyRequest.getCompanyName())
                .postalCode(createOwnerCompanyRequest.getPostalCode())
                .town(createOwnerCompanyRequest.getTown())
                .street(createOwnerCompanyRequest.getStreet())
                .taxNumber(createOwnerCompanyRequest.getTaxNumber())
                .accountNumber(createOwnerCompanyRequest.getAccountNumber())
                .status(createOwnerCompanyRequest.isStatus())
                .build();
    }


}
