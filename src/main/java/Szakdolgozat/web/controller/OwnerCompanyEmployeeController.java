package Szakdolgozat.web.controller;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import Szakdolgozat.service.OwnerCompanyEmployeeService;
import Szakdolgozat.web.dto.ResponseDto;
import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ownercompany")
@Transactional
public class OwnerCompanyEmployeeController {

    private final OwnerCompanyEmployeeService ownerCompanyEmployeeService;
    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/addEmployee")
    public ResponseDto<OwnerCompanyEmployeeEntity> addEmployee(@Valid @RequestBody CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return new ResponseDto<>(bindingResult.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.toSet()));
        }
        return new ResponseDto<>(ownerCompanyEmployeeService.addEmployee(createOwnerCompanyEmployeeRequest));
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findall")
    public Iterable<OwnerCompanyEmployeeEntity> findAllEmployee(){
        return ownerCompanyEmployeeRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/findemployee/{employeename}")
    public ResponseEntity<Set<OwnerCompanyEmployeeEntity>> findEmployeeByName(@PathVariable (value = "employeename") String name) {
        return new ResponseEntity<>(ownerCompanyEmployeeRepository.findByNameContainingIgnoreCase(name), HttpStatus.OK);
    }
}
