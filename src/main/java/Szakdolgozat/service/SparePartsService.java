package Szakdolgozat.service;

import Szakdolgozat.web.model.CreateSparePartsRequest;
import Szakdolgozat.entities.SparePartsEntity;
import Szakdolgozat.repository.SparePartsRepository;
import Szakdolgozat.service.mapper.SparePartsMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
