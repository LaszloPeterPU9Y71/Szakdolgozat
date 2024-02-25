package Szakdolgozat.web.controller;

import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.repository.CompanyRepository;
import Szakdolgozat.service.CompanyService;
import Szakdolgozat.web.model.CreateCompanyRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
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
    public @ResponseBody Iterable<CompanyEntity> findAllCompany() {
        return companyRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addCompany")
    public CompanyEntity createUser(@RequestBody CreateCompanyRequest createCompanyRequest) throws ValidationException {
        return companyService.addCompany(createCompanyRequest);
    }


}
