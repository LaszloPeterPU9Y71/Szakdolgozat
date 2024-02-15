package Szakdolgozat.service;

import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import Szakdolgozat.repository.OwnerCompanyRepository;
import Szakdolgozat.service.mapper.OwnerCompanyEmployeeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerCompanyEmployeeService {

    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;
    private final OwnerCompanyEmployeeMapper ownerCompanyEmployeeMapper;
    private final OwnerCompanyRepository ownerCompanyRepository;


    public OwnerCompanyEmloyeeEntity addEmployee(CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception{
        String email = createOwnerCompanyEmployeeRequest.getEmail();
        Optional<OwnerCompanyEmloyeeEntity> maybeEmployee = ownerCompanyEmployeeRepository.findByEmail(email);

        if(maybeEmployee.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The employee exists with: %s email address", email));
        }
        OwnerCompanyEmloyeeEntity employee = ownerCompanyEmployeeMapper.map(createOwnerCompanyEmployeeRequest);
        employee.setStatus(true);
        employee.setOwnerCompanyEntity(ownerCompanyRepository.findByCompanyNameContaining(createOwnerCompanyEmployeeRequest.getCompanyName()));
        return ownerCompanyEmployeeRepository.save(employee);

    }





}