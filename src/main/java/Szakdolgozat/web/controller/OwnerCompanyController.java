package Szakdolgozat.web.controller;


import Szakdolgozat.service.OwnerCompanyService;
import Szakdolgozat.web.dto.OwnerCompanyDto;
import Szakdolgozat.web.model.CreateOwnerCompanyRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/owner")
@RequiredArgsConstructor
@Transactional
public class OwnerCompanyController {

    private final OwnerCompanyService ownerCompanyService;



    @PostMapping("/add-company")
    public ResponseEntity<OwnerCompanyDto> addOwnerCompany(@RequestBody CreateOwnerCompanyRequest createOwnerCompanyRequest) throws Exception {
        OwnerCompanyDto ownerCompanyDto = ownerCompanyService.addOwnerCompany(createOwnerCompanyRequest);
        return ResponseEntity.ok(ownerCompanyDto);
    }


    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<OwnerCompanyDto>> findAllOwnerCompany() {
        List<OwnerCompanyDto> ownerCompanyDtos = ownerCompanyService.findAllOwnerCompany();
        return ResponseEntity.ok(ownerCompanyDtos);
    }




    @DeleteMapping("/delete/{taxNumber}")
    public ResponseEntity<String> deleteOwnerCompany(@PathVariable("taxNumber") String taxNumber){
        ownerCompanyService.deleteOwnerCompany(taxNumber);
        return ResponseEntity.ok("A cég ezzel az adószámmal törlésre került: " + taxNumber + ".");
    }


    @GetMapping("/find-by-name/{ownerCompanyName}")
    public ResponseEntity<List<OwnerCompanyDto>> findOwnerCompanyByName(@PathVariable("ownerCompanyName") String ownerCompanyName){
        List<OwnerCompanyDto> ownerCompanyDtos = ownerCompanyService.findByOwnerCompanyName(ownerCompanyName);
        return ResponseEntity.ok(ownerCompanyDtos);
    }


    @GetMapping("/find-by-id/{ownerCompanyId}")
    public ResponseEntity<OwnerCompanyDto> findOwnerCompanyById(@PathVariable("ownerCompanyId") long ownerCompanyId){
        OwnerCompanyDto ownerCompanyDto = ownerCompanyService.findOwnerCompanyById(ownerCompanyId);
        return ResponseEntity.ok(ownerCompanyDto);
    }


    @GetMapping("/find-by-taxnumber/{taxNumber}")
    public ResponseEntity<OwnerCompanyDto> findOwnerCompanyByTaxNumber(@PathVariable("taxNumber") String taxNumber){
        OwnerCompanyDto ownerCompanyDtos = ownerCompanyService.findByOwnerCompanyTaxNumber(taxNumber);
        return ResponseEntity.ok(ownerCompanyDtos);
    }

}
