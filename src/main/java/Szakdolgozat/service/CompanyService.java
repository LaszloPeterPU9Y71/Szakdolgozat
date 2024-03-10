package Szakdolgozat.service;

import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.AlreadyRegisteredException;
import Szakdolgozat.repository.CompanyRepository;
import Szakdolgozat.service.mapper.CompanyMapper;
import Szakdolgozat.service.mapper.entityToDto.CompanyMapStructDto;
import Szakdolgozat.web.dto.CompanyDto;
import Szakdolgozat.web.model.CreateCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapStructDto companyMapStructDto;



    public CompanyDto addCompany (CreateCompanyRequest createCompanyRequest) throws AlreadyRegisteredException {
        String taxNumber = createCompanyRequest.getTaxNumber();
        Optional<CompanyEntity> maybeTax = companyRepository.findByTaxNumber(taxNumber);

        if  (maybeTax.isPresent()) {
            throw new AlreadyRegisteredException(String.format("Ezzel az adószámmal már regisztráltak céget: '%s'", taxNumber));
            }
        CompanyEntity company = CompanyMapper.map(createCompanyRequest);
        company.setStatus(true);
        CompanyEntity companyEntity = companyRepository.save(company);
        return companyMapStructDto.fromEntityToDto(companyEntity);
    }
    public Iterable<CompanyEntity> findAllCompany(){
        return companyRepository.findAll();
    }




}
