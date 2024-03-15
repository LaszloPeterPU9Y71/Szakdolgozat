package Szakdolgozat.service;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.ConstraintViolationException;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.repository.OwnerCompanyRepository;
import Szakdolgozat.service.mapper.OwnerCompanyMapper;
import Szakdolgozat.service.mapper.entityToDto.OwnerCompanyMapStructDto;
import Szakdolgozat.web.dto.OwnerCompanyDto;
import Szakdolgozat.web.model.CreateOwnerCompanyRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerCompanyService {

    private final OwnerCompanyRepository ownerCompanyRepository;
    private final OwnerCompanyMapStructDto ownerCompanyMapStructDto;

    public List<OwnerCompanyDto> findAllOwnerCompany(){
        Iterable<OwnerCompanyEntity> ownerCompanyEntities = ownerCompanyRepository.findAll();
        return ownerCompanyMapStructDto.fromEntityToDtoList(ownerCompanyEntities);
    }

    public List<OwnerCompanyDto> findByOwnerCompanyName(String name) throws DataNotFoundException{
        List<OwnerCompanyEntity> ownerCompanyEntities = ownerCompanyRepository.findByCompanyNameContainingIgnoreCase(name);
       if(ownerCompanyEntities.isEmpty()){
           throw new DataNotFoundException("Nem szerepel hasonló nevű cég az adatbázisban");
           }
        return ownerCompanyMapStructDto.fromEntityToDtoList(ownerCompanyEntities);
    }

    public OwnerCompanyDto findByOwnerCompanyTaxNumber(String taxNumber)throws DataNotFoundException{
       OwnerCompanyEntity ownerCompanyEntity = ownerCompanyRepository.findByTaxNumberContaining(taxNumber);
       if (ownerCompanyEntity == null){
           throw new DataNotFoundException(String.format("Nem szerepel az adatbázisban ilyen adószámmal cég: %s !", taxNumber));
       }
    return ownerCompanyMapStructDto.fromEntityToDto(ownerCompanyEntity);
    }


    public OwnerCompanyDto addOwnerCompany(CreateOwnerCompanyRequest createOwnerCompanyRequest)throws ConstraintViolationException {
        String taxNumber = createOwnerCompanyRequest.getTaxNumber();
        Optional<OwnerCompanyEntity> maybeCompany = ownerCompanyRepository.findByTaxNumber(taxNumber);

        if (maybeCompany.isPresent()){
            throw new ConstraintViolationException(String.format("A cég a következő adószámmal már létezik: %s !", taxNumber ));
        }

            OwnerCompanyEntity ownerCompany = OwnerCompanyMapper.map(createOwnerCompanyRequest);
            ownerCompany.setStatus(true);
            OwnerCompanyEntity ownerCompanyEntity = ownerCompanyRepository.save(ownerCompany);
            return ownerCompanyMapStructDto.fromEntityToDto(ownerCompanyEntity);
    }

    public Optional<OwnerCompanyEntity> updateOwnerCompany(String taxNumber, CreateOwnerCompanyRequest createOwnerCompanyRequest) throws DataNotFoundException {
        Optional<OwnerCompanyEntity> maybeOwnerCompany = ownerCompanyRepository.findByTaxNumber(taxNumber);

        if(maybeOwnerCompany.isEmpty()){
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("A tulajdonos cég %s adószámmal nem található", taxNumber));
        }else {
            return Optional.of(ownerCompanyRepository.save(updateOwnerCompany(maybeOwnerCompany.get(), createOwnerCompanyRequest)));
        }
    }

    private OwnerCompanyEntity updateOwnerCompany(OwnerCompanyEntity current, CreateOwnerCompanyRequest createOwnerCompanyRequest){
        current.setAccountNumber(createOwnerCompanyRequest.getAccountNumber());
        current.setCompanyName(createOwnerCompanyRequest.getCompanyName());
        current.setStreet(createOwnerCompanyRequest.getStreet());
        current.setTown(createOwnerCompanyRequest.getTown());
        current.setPostalCode(createOwnerCompanyRequest.getPostalCode());
        current.setTaxNumber(createOwnerCompanyRequest.getTaxNumber());
        current.setStatus(createOwnerCompanyRequest.isStatus());
        return current;
    }


    public void deleteOwnerCompany(String taxNumber) throws DataNotFoundException{
        if(!ownerCompanyRepository.existsByTaxNumber(taxNumber)){
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("A következő adószámú céget nem találom: %s !", taxNumber));

        }else{
            ownerCompanyRepository.deleteByTaxNumber(taxNumber);
        }
    }
}


