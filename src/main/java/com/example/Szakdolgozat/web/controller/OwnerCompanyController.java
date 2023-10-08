package com.example.Szakdolgozat.web.controller;


import com.example.Szakdolgozat.entities.OwnerCompanyEntity;
import com.example.Szakdolgozat.repository.OwnerCompanyRepository;
import com.example.Szakdolgozat.service.OwnerCompanyService;
import com.example.Szakdolgozat.web.model.CreateOwnerCompanyRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/owner")
@RequiredArgsConstructor
@Transactional
public class OwnerCompanyController {

    private final OwnerCompanyRepository ownerCompanyRepository;
    private final OwnerCompanyService ownerCompanyService;


    @PostMapping("/addCompany")
    public OwnerCompanyEntity addOwnerCompany(@RequestBody CreateOwnerCompanyRequest createOwnerCompanyRequest){
        return ownerCompanyService.addOwnerCompany(createOwnerCompanyRequest);
    }
    @GetMapping("/all")
    public @ResponseBody Iterable<OwnerCompanyEntity> findAllOwnerCompany(){return ownerCompanyRepository.findAll();}

    @DeleteMapping("/delete/{taxNumber}")
    public String deleteOwnerCompany(@PathVariable("taxNumber") String taxNumber){
        ownerCompanyService.deleteOwnerCompany(taxNumber);
        return "Owner company with tax number: " + taxNumber + " has been deleted";
    }

    @GetMapping("/findbyname/{ownerCompanyName}")
    public ResponseEntity<List<OwnerCompanyEntity>> findOwnerCOmpanyByName(@PathVariable("ownerCompanyName") String ownerCompanyName){
        return new ResponseEntity<>( ownerCompanyRepository.findByName(ownerCompanyName), HttpStatus.OK);
    }

    @GetMapping("/findbytaxnumber/{taxNumber}")
    public ResponseEntity<Optional<OwnerCompanyEntity>> findOwnerCompanyByTaxNumber(@PathVariable("taxNumber") String taxNumber){
        return new ResponseEntity<>(ownerCompanyRepository.findByTaxNumber(taxNumber), HttpStatus.OK);
    }

}
