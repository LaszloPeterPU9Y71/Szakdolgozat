package com.example.Szakdolgozat.service;

import com.example.Szakdolgozat.entities.SparePartsEntity;
import com.example.Szakdolgozat.repository.SparePartsRepository;
import com.example.Szakdolgozat.service.mapper.SparePartsMapper;
import com.example.Szakdolgozat.web.model.CreateSparePartsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class SparePartsService {


    private final SparePartsRepository sparePartsRepository;

    private final  SparePartsMapper sparePartsMapper;



    public SparePartsEntity addSparePart(CreateSparePartsRequest createSparePartsRequest) throws Exception {
        String partNumber = createSparePartsRequest.getPartNumber();
        Optional<SparePartsEntity> maybeSparePart = sparePartsRepository.findByPartNumber(partNumber);
        if(maybeSparePart.isPresent()) {
            throw new Exception(String.format("This item number is already exists", partNumber));
        }
        SparePartsEntity spareParts = sparePartsMapper.map(createSparePartsRequest);
        return sparePartsRepository.save(spareParts);
    }
}
