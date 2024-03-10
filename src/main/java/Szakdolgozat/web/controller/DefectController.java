package Szakdolgozat.web.controller;

import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.repository.DefectRepository;
import Szakdolgozat.service.DefectService;
import Szakdolgozat.web.dto.DefectDto;
import Szakdolgozat.web.model.CreateDefectRequest;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/defects")
@RequiredArgsConstructor

public class DefectController {

    private final DefectService defectService;
    private final DefectRepository defectRepository;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public ResponseEntity<DefectDto> createDefect(@RequestBody CreateDefectRequest createDefectRequest) throws Exception {
        DefectDto defectDto = defectService.addDefect(createDefectRequest);
        return ResponseEntity.ok(defectDto);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<DefectDto>> findAllDefects(){
        List<DefectDto> defectDtos = defectService.findAllDefects();
        return ResponseEntity.ok(defectDtos);

    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/name/{defectName}")
    public ResponseEntity<List<DefectDto>> findDefectsByName(@PathVariable(value = "defectName") String name) {
        List<DefectDto> defectDtos = defectService.findDefectsByName(name);
        return ResponseEntity.ok(defectDtos);
    }
}