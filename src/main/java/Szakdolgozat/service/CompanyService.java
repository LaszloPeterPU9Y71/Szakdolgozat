package Szakdolgozat.service;

import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.globalExceptionHandler.AlreadyRegisteredException;
import Szakdolgozat.globalExceptionHandler.MissingDataException;
import Szakdolgozat.repository.CompanyRepository;
import Szakdolgozat.service.mapper.CompanyMapper;
import Szakdolgozat.web.dto.CompanyDto;
import Szakdolgozat.web.model.CreateCompanyRequest;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;



    public CompanyEntity addCompany (CreateCompanyRequest createCompanyRequest) throws ValidationException {
        String taxNumber = createCompanyRequest.getTaxNumber();
        Optional<CompanyEntity> maybeTax = companyRepository.findByTaxNumber(taxNumber);

        if  (maybeTax.isPresent()) {
            throw new AlreadyRegisteredException(String.format("Ezzel az adószámmal már regisztráltaka céget: '%s'", taxNumber), HttpStatus.CONFLICT);
            }
        CompanyEntity company = CompanyMapper.map(createCompanyRequest);
        company.setStatus(true);

        return companyRepository.save(company);
    }
    public Iterable<CompanyEntity> findAllCompany(){
        return companyRepository.findAll();
    }




}
