package com.example.Szakdolgozat.service;

import com.example.Szakdolgozat.entities.OwnerCompanyEntity;
import com.example.Szakdolgozat.repository.OwnerCompanyRepository;
import com.example.Szakdolgozat.service.mapper.OwnerCompanyMapper;
import com.example.Szakdolgozat.web.model.CreateOwnerCompanyRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerCompanyService {

    private final OwnerCompanyRepository ownerCompanyRepository;

    public OwnerCompanyEntity addOwnerCompany(CreateOwnerCompanyRequest createOwnerCompanyRequest){
        String taxNumber = createOwnerCompanyRequest.getTaxNumber();
        Optional<OwnerCompanyEntity> maybeCompany = ownerCompanyRepository.findByTaxNumber(taxNumber);

        if (maybeCompany.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The company with tax number: %s is already exists", taxNumber ));
        }
            OwnerCompanyEntity ownerCompany = OwnerCompanyMapper.map(createOwnerCompanyRequest);
            ownerCompany.setStatus(true);
            return ownerCompanyRepository.save(ownerCompany);
    }

    public Optional<OwnerCompanyEntity> updateOwnerCompany(String taxNumber, CreateOwnerCompanyRequest createOwnerCompanyRequest){
        Optional<OwnerCompanyEntity> maybeOwnerCompany = ownerCompanyRepository.findByTaxNumber(taxNumber);

        if(maybeOwnerCompany.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Owner company with tax number %s not found", taxNumber));
        }else {
            return Optional.of(ownerCompanyRepository.save(updateOwnerCompany(maybeOwnerCompany.get(), createOwnerCompanyRequest)));
        }
    }

    private OwnerCompanyEntity updateOwnerCompany(OwnerCompanyEntity current, CreateOwnerCompanyRequest createOwnerCompanyRequest){
        current.setAccountNumber(createOwnerCompanyRequest.getAccountNumber());
        current.setName(createOwnerCompanyRequest.getName());
        current.setStreet(createOwnerCompanyRequest.getStreet());
        current.setTown(createOwnerCompanyRequest.getTown());
        current.setPostalCode(createOwnerCompanyRequest.getPostalCode());
        current.setTaxNumber(createOwnerCompanyRequest.getTaxNumber());
        current.setStatus(createOwnerCompanyRequest.isStatus());
        return current;
    }


    public void deleteOwnerCompany(String taxNumber) {
        if(!ownerCompanyRepository.existsByTaxNumber(taxNumber)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Company with tax number: %s not found", taxNumber));

        }else{
            ownerCompanyRepository.deleteByTaxNumber(taxNumber);
        }
    }
}


