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
@RequestMapping("/api/v1/owner-company-employee")
@Transactional
@CrossOrigin(origins = "http://localhost:4200/")
public class OwnerCompanyEmployeeController {

    private final OwnerCompanyEmployeeService ownerCompanyEmployeeService;
    private final OwnerCompanyService ownerCompanyService;


    @PostMapping("/add-employee")
    public ResponseEntity<OwnerCompanyEmployeeDto> addEmployee(@Valid @RequestBody CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception {
        OwnerCompanyEmployeeDto ownerCompanyEmployeeDto = ownerCompanyEmployeeService.addEmployee(createOwnerCompanyEmployeeRequest);
        return ResponseEntity.ok(ownerCompanyEmployeeDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<OwnerCompanyEmployeeDto>> findAllOwnerCompanyEmployee(){
        List<OwnerCompanyEmployeeDto> ownerCompanyEmployeeDtos = ownerCompanyEmployeeService.findAllOwnerCompanyEmployee();
        return ResponseEntity.ok(ownerCompanyEmployeeDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/find-employee/{employeename}")
    public ResponseEntity<List<OwnerCompanyEmployeeDto>> findEmployeeByName(@PathVariable (value = "employeename") String name) {
        List<OwnerCompanyEmployeeDto> ownerCompanyEmployeeDtos = ownerCompanyEmployeeService.findOwnerCompanyEmployeeByName(name);
        return ResponseEntity.ok(ownerCompanyEmployeeDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/company-assign-employee/{companyId}/{employeeId}")
    public ResponseEntity<String> companyAssignEmployee(@PathVariable (value = "companyId") long companyId, @PathVariable (value = "employeeId") long employeeId) {
        ownerCompanyEmployeeService.companyAssignEmployee(companyId, employeeId);
        return ResponseEntity.ok("Az alkalmazotthoz hozzárendelésre kerlült cégehez.");


    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/company-unassign-employee/{employeeId}")
    public ResponseEntity<String> companyAssignEmployee(@PathVariable (value = "employeeId") long employeeId) {
        ownerCompanyEmployeeService.companyUnassignEmployee(employeeId);
        return ResponseEntity.ok("A cég és alkalmazott összerendelés megszűnt.");


    }


}
