package com.example.Szakdolgozat.web.controller;

import com.example.Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import com.example.Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import com.example.Szakdolgozat.service.OwnerCompanyEmployeeService;
import com.example.Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ownercompany")
@Transactional
public class OwnerCompanyEmployeeController {

    private final OwnerCompanyEmployeeService ownerCompanyEmployeeService;
    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addEmployee")
    public OwnerCompanyEmloyeeEntity addEmployee(@RequestBody CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception {
        return ownerCompanyEmployeeService.addEmployee(createOwnerCompanyEmployeeRequest);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findall")
    public Iterable<OwnerCompanyEmloyeeEntity> findAllEmployee(){
        return ownerCompanyEmployeeRepository.findAll();
    }



}
