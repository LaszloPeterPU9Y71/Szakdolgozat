package Szakdolgozat.web.controller;

import Szakdolgozat.repository.CompanyRepository;
import Szakdolgozat.service.CompanyService;
import Szakdolgozat.service.mapper.entityToDto.CompanyMapStructDto;
import Szakdolgozat.web.dto.CompanyDto;
import Szakdolgozat.web.model.CreateCompanyRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
@Transactional

public class CompanyController {


    private final CompanyService companyService;


    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>> findAllCompanies(){
            List<CompanyDto> companyDtos = companyService.findAllCompanies();
            return ResponseEntity.ok(companyDtos);

    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addCompany")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CreateCompanyRequest createCompanyRequest) throws Exception {
        CompanyDto companyDto = companyService.addCompany(createCompanyRequest);
        return ResponseEntity.ok(companyDto);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findCompaniesByName/{companyName}")
    public ResponseEntity<List<CompanyDto>> findAllCompaniesByName(@PathVariable (value="companyName") String name){
        List<CompanyDto> companyDtos = companyService.findAllCompaniesByName(name);
        return ResponseEntity.ok(companyDtos);

    }




}
