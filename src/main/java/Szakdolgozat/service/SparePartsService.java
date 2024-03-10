package Szakdolgozat.service;

import Szakdolgozat.service.mapper.entityToDto.SparePartsMapStructDto;
import Szakdolgozat.web.dto.SparePartsDto;
import Szakdolgozat.web.model.CreateSparePartsRequest;
import Szakdolgozat.entities.SparePartsEntity;
import Szakdolgozat.repository.SparePartsRepository;
import Szakdolgozat.service.mapper.SparePartsMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SparePartsService {


    private final SparePartsRepository sparePartsRepository;
    private final  SparePartsMapper sparePartsMapper;
    private final SparePartsMapStructDto sparePartsMapStructDto;


    public List<SparePartsDto> findAllSpareParts(){
        Iterable<SparePartsEntity> sparePartsEntities = sparePartsRepository.findAll();
        return sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities);
    }

    public List<SparePartsDto> findSparePartsByName(String partName){
        Iterable<SparePartsEntity> sparePartsEntities = sparePartsRepository.findByPartNameIsContainingIgnoreCase(partName);
        return sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities);
    }

    public List<SparePartsDto> findSparePartsByNumber(String partNumber){
        Iterable<SparePartsEntity> sparePartsEntities = sparePartsRepository.findByPartNumberContaining(partNumber);
        return sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities);
    }

    public SparePartsDto addSparePart(CreateSparePartsRequest createSparePartsRequest) throws Exception {
        String partNumber = createSparePartsRequest.getPartNumber();
        Optional<SparePartsEntity> maybeSparePart = sparePartsRepository.findByPartNumberContainingIgnoreCase(partNumber);
        if(maybeSparePart.isPresent()) {
            throw new Exception(String.format("Ez a cikkszám már létezik: %s", partNumber));
        }
        SparePartsEntity spareParts = sparePartsMapper.map(createSparePartsRequest);
        SparePartsEntity sparePartsEntity = sparePartsRepository.save(spareParts);
        return sparePartsMapStructDto.fromEntityToDto(sparePartsEntity);
    }

    public void deleteSparePart(String partNumber){
        if(sparePartsRepository.findByPartNumberContainingIgnoreCase(partNumber).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Nem találom ezt a cikkszámú alkatrészt: %s", partNumber));
        }else{
            sparePartsRepository.deleteByPartNumber(partNumber);
        }
    }

    public Optional<SparePartsEntity> updateSparePartData(String partNumber, CreateSparePartsRequest createSparePartsRequest){
        Optional<SparePartsEntity> maybeSparePartEntity = sparePartsRepository.findByPartNumberContainingIgnoreCase(partNumber);

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
