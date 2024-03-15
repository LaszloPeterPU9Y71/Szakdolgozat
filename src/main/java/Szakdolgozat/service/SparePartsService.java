package Szakdolgozat.service;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.ConstraintViolationException;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataAlreadyExistsException;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
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
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SparePartsService {


    private final SparePartsRepository sparePartsRepository;
    private final  SparePartsMapper sparePartsMapper;
    private final SparePartsMapStructDto sparePartsMapStructDto;


    public List<SparePartsDto> findAllSpareParts() throws DataNotFoundException{
        Iterable<SparePartsEntity> sparePartsEntities = sparePartsRepository.findAll();
        if(sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities).isEmpty()){
            throw new DataNotFoundException("Az adatbázis még üres!");
        }
        return sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities);
    }

    public List<SparePartsDto> findSparePartsByName(String partName) throws DataNotFoundException {
        Iterable<SparePartsEntity> sparePartsEntities = sparePartsRepository.findByPartNameIsContainingIgnoreCase(partName);
        if(sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities).isEmpty()){
            throw new DataNotFoundException(String.format("Ezzel a névvel nem találtam alkatrészt: %s !", partName));
        }
        return sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities);
    }

    public List<SparePartsDto> findSparePartsByNumber(String partNumber){
        Iterable<SparePartsEntity> sparePartsEntities = sparePartsRepository.findByPartNumberContaining(partNumber);
        if(sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities).isEmpty()){
            throw new DataNotFoundException(String.format("Ezzel a cikkszámmal nem találtam alkatrészt: %s !", partNumber));
        }
        return sparePartsMapStructDto.fromEntitytoDtoList(sparePartsEntities);
    }

    public SparePartsDto addSparePart(CreateSparePartsRequest createSparePartsRequest) throws ConstraintViolationException {
        String partNumber = createSparePartsRequest.getPartNumber();
        Optional<SparePartsEntity> maybeSparePart = sparePartsRepository.findByPartNumberContainingIgnoreCase(partNumber);
        if(maybeSparePart.isPresent()) {
            throw new ConstraintViolationException(String.format("Ez a cikkszám már létezik: %s", partNumber));
        }
        SparePartsEntity spareParts = sparePartsMapper.map(createSparePartsRequest);
        SparePartsEntity sparePartsEntity = sparePartsRepository.save(spareParts);
        return sparePartsMapStructDto.fromEntityToDto(sparePartsEntity);
    }

    public void deleteSparePart(String partNumber) throws DataNotFoundException{
        if(sparePartsRepository.findByPartNumberContainingIgnoreCase(partNumber).isEmpty()){
            throw new DataNotFoundException(String.format("Nem találom ezt a cikkszámú alkatrészt: %s", partNumber));
        }
        sparePartsRepository.deleteByPartNumber(partNumber);
    }


    public void updateSparePartData(String number, CreateSparePartsRequest createSparePartsRequest) throws DataNotFoundException{
        Optional<SparePartsEntity> maybeSparePartEntity = sparePartsRepository.findByPartNumber(number);

        if(maybeSparePartEntity.isEmpty()) {
            throw new DataNotFoundException(String.format("Ezzel a cikkszámmal nem találtam alkatrészt: %s", number));


        }
        sparePartsRepository.save(updateSparePartData(maybeSparePartEntity.get(), createSparePartsRequest));
    }

    private SparePartsEntity updateSparePartData(SparePartsEntity current, CreateSparePartsRequest createSparePartsRequest) throws DataAlreadyExistsException {
        Optional<SparePartsEntity> maybeSpareParts = sparePartsRepository.findByPartNumber(createSparePartsRequest.getPartNumber());
        if(maybeSpareParts.isPresent()){
            throw new DataAlreadyExistsException(String.format("Ez a cikkszám foglalt: %s ", createSparePartsRequest.getPartNumber()));
        }
        current.setPartName(createSparePartsRequest.getPartName());
        current.setPartNumber(createSparePartsRequest.getPartNumber());
        current.setNettoBuyingPrice(createSparePartsRequest.getNettoBuyingPrice());
        current.setNettoSellingPrice(createSparePartsRequest.getNettoSellingPrice());
        return current;
    }



}
