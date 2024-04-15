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
import java.util.Optional;


@RestController
@RequestMapping("api/v1/company")
@RequiredArgsConstructor
@Transactional

public class CompanyController {


    private final CompanyService companyService;



    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>> findAllCompanies(){
            List<CompanyDto> companyDtos = companyService.findAllCompanies();
            return ResponseEntity.ok(companyDtos);

    }


    @PostMapping("/create")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CreateCompanyRequest createCompanyRequest) throws Exception {
        CompanyDto companyDto = companyService.addCompany(createCompanyRequest);
        return ResponseEntity.ok(companyDto);
    }


    @GetMapping("/find-by-name/{companyName}")
    public ResponseEntity<List<CompanyDto>> findAllCompaniesByName(@PathVariable (value="companyName") String name){
        List<CompanyDto> companyDtos = companyService.findAllCompaniesByName(name);
        return ResponseEntity.ok(companyDtos);

    }


    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<CompanyDto> findCompanyById(@PathVariable (value="id") Long id){
        CompanyDto companyDtos = companyService.findById(id);
        return ResponseEntity.ok(companyDtos);

    }




}
