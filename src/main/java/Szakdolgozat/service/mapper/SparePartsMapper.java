package Szakdolgozat.service.mapper;

import Szakdolgozat.web.model.CreateSparePartsRequest;
import Szakdolgozat.entities.SparePartsEntity;
import org.springframework.stereotype.Component;

@Component
public class SparePartsMapper {

    public SparePartsEntity map(CreateSparePartsRequest createSparePartsRequest){
        return SparePartsEntity.builder()
                .partName(createSparePartsRequest.getPartName())
                .partNumber(createSparePartsRequest.getPartNumber())
                .nettoBuyingPrice((createSparePartsRequest.getNettoBuyingPrice()))
                .nettoSellingPrice((createSparePartsRequest.getNettoSellingPrice()))
                .build();
    }

}
