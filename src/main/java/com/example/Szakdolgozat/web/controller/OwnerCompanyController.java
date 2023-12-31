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

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addCompany")
    public OwnerCompanyEntity addOwnerCompany(@RequestBody CreateOwnerCompanyRequest createOwnerCompanyRequest){
        return ownerCompanyService.addOwnerCompany(createOwnerCompanyRequest);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public @ResponseBody Iterable<OwnerCompanyEntity> findAllOwnerCompany(){return ownerCompanyRepository.findAll();}
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/delete/{taxNumber}")
    public String deleteOwnerCompany(@PathVariable("taxNumber") String taxNumber){
        ownerCompanyService.deleteOwnerCompany(taxNumber);
        return "Owner company with tax number: " + taxNumber + " has been deleted";
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findbyname/{ownerCompanyName}")
    public ResponseEntity<List<OwnerCompanyEntity>> findOwnerCompanyByName(@PathVariable("ownerCompanyName") String ownerCompanyName){
        return new ResponseEntity<>( ownerCompanyRepository.findByNameContainingIgnoreCase(ownerCompanyName), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findbytaxnumber/{taxNumber}")
    public ResponseEntity<Optional<OwnerCompanyEntity>> findOwnerCompanyByTaxNumber(@PathVariable("taxNumber") String taxNumber){
        return new ResponseEntity<>(ownerCompanyRepository.findByTaxNumber(taxNumber), HttpStatus.OK);
    }

}
