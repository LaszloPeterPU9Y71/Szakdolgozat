package Szakdolgozat.web.controller;


import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.repository.OwnerCompanyRepository;
import Szakdolgozat.service.OwnerCompanyService;
import Szakdolgozat.web.dto.OwnerCompanyDto;
import Szakdolgozat.web.model.CreateOwnerCompanyRequest;
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
    public ResponseEntity<OwnerCompanyDto> addOwnerCompany(@RequestBody CreateOwnerCompanyRequest createOwnerCompanyRequest) throws Exception {
        OwnerCompanyDto ownerCompanyDto = ownerCompanyService.addOwnerCompany(createOwnerCompanyRequest);
        return ResponseEntity.ok(ownerCompanyDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public @ResponseBody Iterable<OwnerCompanyEntity> findAllOwnerCompany() {
        return ownerCompanyRepository.findAll();
    }



    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/delete/{taxNumber}")
    public String deleteOwnerCompany(@PathVariable("taxNumber") String taxNumber){
        ownerCompanyService.deleteOwnerCompany(taxNumber);
        return "Owner company with tax number: " + taxNumber + " has been deleted";
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findbyname/{ownerCompanyName}")
    public ResponseEntity<List<OwnerCompanyEntity>> findOwnerCompanyByName(@PathVariable("ownerCompanyName") String ownerCompanyName){
        return new ResponseEntity<>( ownerCompanyRepository.findByCompanyNameContainingIgnoreCase(ownerCompanyName), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findbytaxnumber/{taxNumber}")
    public ResponseEntity<Optional<OwnerCompanyEntity>> findOwnerCompanyByTaxNumber(@PathVariable("taxNumber") String taxNumber){
        return new ResponseEntity<>(ownerCompanyRepository.findByTaxNumber(taxNumber), HttpStatus.OK);
    }

}
