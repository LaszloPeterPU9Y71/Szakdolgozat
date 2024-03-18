package Szakdolgozat.web.controller;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.service.OwnerCompanyEmployeeService;
import Szakdolgozat.service.OwnerCompanyService;
import Szakdolgozat.web.dto.OwnerCompanyDto;
import Szakdolgozat.web.dto.OwnerCompanyEmployeeDto;
import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ownercompany")
@Transactional
public class OwnerCompanyEmployeeController {

    private final OwnerCompanyEmployeeService ownerCompanyEmployeeService;
    private final OwnerCompanyService ownerCompanyService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addEmployee")
    public ResponseEntity<OwnerCompanyEmployeeDto> addEmployee(@Valid @RequestBody CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception {
        OwnerCompanyEmployeeDto ownerCompanyEmployeeDto = ownerCompanyEmployeeService.addEmployee(createOwnerCompanyEmployeeRequest);
        return ResponseEntity.ok(ownerCompanyEmployeeDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findall")
    public ResponseEntity<List<OwnerCompanyEmployeeDto>> findAllOwnerCompany(){
        List<OwnerCompanyEmployeeDto> ownerCompanyEmployeeDtos = ownerCompanyEmployeeService.findAllOwnerCompany();
        return ResponseEntity.ok(ownerCompanyEmployeeDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findemployee/{employeename}")
    public ResponseEntity<List<OwnerCompanyEmployeeDto>> findEmployeeByName(@PathVariable (value = "employeename") String name) {
        List<OwnerCompanyEmployeeDto> ownerCompanyEmployeeDtos = ownerCompanyEmployeeService.findOwnerCompanyByName(name);
        return ResponseEntity.ok(ownerCompanyEmployeeDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/company+employee/")
    public void companyConnectEmployee(long companyId, long companyEmployeeId){
        OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity = ownerCompanyEmployeeService.findOwnerCompanyEmployeeById(companyEmployeeId);
        OwnerCompanyEntity ownerCompanyEntity = ownerCompanyService.findOwnerCompanyById(companyId);
        ownerCompanyEmployeeEntity.setOwnerCompanyEntity(ownerCompanyEntity);
    }



}
