package Szakdolgozat.web.controller;

import Szakdolgozat.service.OwnerCompanyEmployeeService;
import Szakdolgozat.service.OwnerCompanyService;
import Szakdolgozat.web.dto.OwnerCompanyEmployeeDto;
import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ownercompany")
@Transactional
@CrossOrigin(origins = "http://localhost:4200/")
public class OwnerCompanyEmployeeController {

    private final OwnerCompanyEmployeeService ownerCompanyEmployeeService;
    private final OwnerCompanyService ownerCompanyService;


    @PostMapping("/addEmployee")
    public ResponseEntity<OwnerCompanyEmployeeDto> addEmployee(@Valid @RequestBody CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception {
        OwnerCompanyEmployeeDto ownerCompanyEmployeeDto = ownerCompanyEmployeeService.addEmployee(createOwnerCompanyEmployeeRequest);
        return ResponseEntity.ok(ownerCompanyEmployeeDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findall")
    public ResponseEntity<List<OwnerCompanyEmployeeDto>> findAllOwnerCompanyEmployee(){
        List<OwnerCompanyEmployeeDto> ownerCompanyEmployeeDtos = ownerCompanyEmployeeService.findAllOwnerCompanyEmployee();
        return ResponseEntity.ok(ownerCompanyEmployeeDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findemployee/{employeename}")
    public ResponseEntity<List<OwnerCompanyEmployeeDto>> findEmployeeByName(@PathVariable (value = "employeename") String name) {
        List<OwnerCompanyEmployeeDto> ownerCompanyEmployeeDtos = ownerCompanyEmployeeService.findOwnerCompanyEmployeeByName(name);
        return ResponseEntity.ok(ownerCompanyEmployeeDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/company+employee/{companyId}/{employeeId}")
    public ResponseEntity<String> companyAssignEmployee(@PathVariable (value = "companyId") long companyId, @PathVariable (value = "employeeId") long employeeId) {
        ownerCompanyEmployeeService.companyAssignEmployee(companyId, employeeId);
        return ResponseEntity.ok("Az alkalmazotthoz hozzárendelésre kerlült cégehez.");


    }



}
