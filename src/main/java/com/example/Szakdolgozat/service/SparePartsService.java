package com.example.Szakdolgozat.service;

import com.example.Szakdolgozat.entities.SparePartsEntity;
import com.example.Szakdolgozat.repository.SparePartsRepository;
import com.example.Szakdolgozat.repository.ToolRepository;
import com.example.Szakdolgozat.service.mapper.SparePartsMapper;
import com.example.Szakdolgozat.web.model.CreateSparePartsRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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

    public void deleteSparePart(String partNumber){
        if(sparePartsRepository.findByPartNumber(partNumber).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Spare part not found with number: %s", partNumber));
        }else{
            sparePartsRepository.deleteByPartNumber(partNumber);
        }
    }

    public Optional<SparePartsEntity> updateSparePartData(String partNumber, CreateSparePartsRequest createSparePartsRequest){
        Optional<SparePartsEntity> maybeSparePartEntity = sparePartsRepository.findByPartNumber(partNumber);

        if(maybeSparePartEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Spare part not found with number: %s", partNumber));
        }
        return Optional.of(sparePartsRepository.save(updateSparePartData(maybeSparePartEntity.get(), createSparePartsRequest)));
    }

    public SparePartsEntity updateSparePartData(SparePartsEntity current, CreateSparePartsRequest createSparePartsRequest){
        current.setPartName(createSparePartsRequest.getPartName());
        current.setPartNumber(createSparePartsRequest.getPartNumber());
        current.setNettoBuyingPrice(createSparePartsRequest.getNettoBuyingPrice());
        current.setNettoSellingPrice(createSparePartsRequest.getNettoSellingPrice());
        return current;
    }



}
