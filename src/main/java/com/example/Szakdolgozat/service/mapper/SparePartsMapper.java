package com.example.Szakdolgozat.service.mapper;

import com.example.Szakdolgozat.entities.SparePartsEntity;
import com.example.Szakdolgozat.web.model.CreateSparePartsRequest;
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
