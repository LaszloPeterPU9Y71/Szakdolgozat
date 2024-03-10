package Szakdolgozat.web.controller;


import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.repository.OwnerCompanyRepository;
import Szakdolgozat.service.OwnerCompanyService;
import Szakdolgozat.service.mapper.entityToDto.OwnerCompanyMapStructDto;
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
    private final OwnerCompanyMapStructDto ownerCompanyMapStructDto;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addCompany")
    public ResponseEntity<OwnerCompanyDto> addOwnerCompany(@RequestBody CreateOwnerCompanyRequest createOwnerCompanyRequest) throws Exception {
        OwnerCompanyDto ownerCompanyDto = ownerCompanyService.addOwnerCompany(createOwnerCompanyRequest);
        return ResponseEntity.ok(ownerCompanyDto);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<OwnerCompanyDto>> findAllOwnerCompany() {
        List<OwnerCompanyDto> ownerCompanyDtos = ownerCompanyService.findAllOwnerCompany();
        return ResponseEntity.ok(ownerCompanyDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/delete/{taxNumber}")
    public String deleteOwnerCompany(@PathVariable("taxNumber") String taxNumber){
        ownerCompanyService.deleteOwnerCompany(taxNumber);
        return "A cég ezzel az adószámmal törlésre került: " + taxNumber + ".";
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findbyname/{ownerCompanyName}")
    public ResponseEntity<List<OwnerCompanyDto>> findOwnerCompanyByName(@PathVariable("ownerCompanyName") String ownerCompanyName){
        List<OwnerCompanyDto> ownerCompanyDtos = ownerCompanyService.findByOwnerCompanyName(ownerCompanyName);
        return ResponseEntity.ok(ownerCompanyDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findbytaxnumber/{taxNumber}")
    public ResponseEntity<OwnerCompanyDto> findOwnerCompanyByTaxNumber(@PathVariable("taxNumber") String taxNumber){
        OwnerCompanyDto ownerCompanyDtos = ownerCompanyService.findByOwnerCompanyTaxNumber(taxNumber);
        return ResponseEntity.ok(ownerCompanyDtos);
    }

}
