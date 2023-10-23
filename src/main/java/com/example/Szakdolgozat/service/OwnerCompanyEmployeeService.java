package com.example.Szakdolgozat.service;

import com.example.Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import com.example.Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import com.example.Szakdolgozat.service.mapper.OwnerCompanyEmployeeMapper;
import com.example.Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerCompanyEmployeeService {

    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;
    private final OwnerCompanyEmployeeMapper ownerCompanyEmployeeMapper;


    public OwnerCompanyEmloyeeEntity addEmployee(CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception{
        String email = createOwnerCompanyEmployeeRequest.getEmail();
        Optional<OwnerCompanyEmloyeeEntity> maybeEmployee = ownerCompanyEmployeeRepository.findByEmail(email);

        if(maybeEmployee.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The employee exists with: %s email address", email));
        }
        OwnerCompanyEmloyeeEntity employee = ownerCompanyEmployeeMapper.map(createOwnerCompanyEmployeeRequest);
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        employee.setStatus(true);
        return ownerCompanyEmployeeRepository.save(employee);

    }





}
