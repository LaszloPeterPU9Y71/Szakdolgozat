package Szakdolgozat.web.controller;

import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.repository.CompanyRepository;
import Szakdolgozat.service.CompanyService;
import Szakdolgozat.web.dto.CompanyDto;
import Szakdolgozat.web.model.CreateCompanyRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
@Transactional

public class CompanyController {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> dtos = new ArrayList<>();

        // Fetch entities from repository and convert to DTOs
        Iterable<CompanyEntity> companies = companyRepository.findAll();
        for (CompanyEntity company : companies) {
            dtos.add(new CompanyDto()); // Assuming CompanyDto constructor takes CompanyEntity
        }

        if (dtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addCompany")
    public ResponseEntity<CompanyDto> createUser(@RequestBody CreateCompanyRequest createCompanyRequest) throws ValidationException {

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
