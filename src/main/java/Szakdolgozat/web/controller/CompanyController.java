package Szakdolgozat.web.controller;

import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.repository.CompanyRepository;
import Szakdolgozat.service.CompanyService;
import Szakdolgozat.web.dto.CompanyDto;
import Szakdolgozat.web.model.CreateCompanyRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
@Transactional

public class CompanyController {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public @ResponseBody Iterable<CompanyEntity> findAllComapny() {
        return companyRepository.findAll();
    }


    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addCompany")
    public ResponseEntity<CompanyDto> createUser(@RequestBody CreateCompanyRequest createCompanyRequest) throws Exception {

        var x =  companyService.addCompany(createCompanyRequest);
        CompanyDto companyDto = CompanyDto.builder()
                .id(x.getId())
                .status(x.getStatus())
                .street(x.getStreet())
                .town(x.getTown())
                .taxNumber(x.getTaxNumber())
                .postalCode(x.getPostalCode())
                .name(x.getName())
                .build();
        return ResponseEntity.ok(companyDto);
    }


}
