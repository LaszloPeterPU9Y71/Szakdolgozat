package Szakdolgozat.service;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
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


    public OwnerCompanyEmployeeEntity addEmployee(CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception{
        String email = createOwnerCompanyEmployeeRequest.getEmail();
        Optional<OwnerCompanyEmployeeEntity> maybeEmployee = ownerCompanyEmployeeRepository.findByEmail(email);

        if(maybeEmployee.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The employee exists with: %s email address", email));
        }

        OwnerCompanyEmployeeEntity employee = ownerCompanyEmployeeMapper.map(createOwnerCompanyEmployeeRequest);
        employee.setStatus(true);
        return ownerCompanyEmployeeRepository.save(employee);

    }





}
