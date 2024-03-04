package Szakdolgozat.web.controller;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import Szakdolgozat.service.OwnerCompanyEmployeeService;
import Szakdolgozat.web.dto.OwnerCompanyEmployeeDto;
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
    public ResponseEntity<OwnerCompanyEmployeeDto> addEmployee(@Valid @RequestBody CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception {

        var x = ownerCompanyEmployeeService.addEmployee(createOwnerCompanyEmployeeRequest);
        OwnerCompanyEmployeeDto ownerCompanyEmployeeDto = OwnerCompanyEmployeeDto.builder()
                .email(x.getEmail())
                .id(x.getId())
                .status(x.getStatus())
                .title(x.getTitle())
                .telNum(x.getTelNum())
                .name(x.getName())
                 .build();
        return ResponseEntity.ok(ownerCompanyEmployeeDto);
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
