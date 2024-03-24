package Szakdolgozat.web.controller;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.entities.UserEntity;
import Szakdolgozat.service.WorksheetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/worksheet")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")





public class WorksheetController {

    private final WorksheetService worksheetService;

    @GetMapping("/employee-and-company-data/{name}")
    public ResponseEntity<Iterable<OwnerCompanyEmployeeEntity>> findUserAndCompanyData(@PathVariable("name") String name){
        Iterable<OwnerCompanyEmployeeEntity> ownerCompanyEmployeeEntities = worksheetService.worksheetUserAndCompanyData(name);

        return ResponseEntity.ok(ownerCompanyEmployeeEntities);
    }
}
