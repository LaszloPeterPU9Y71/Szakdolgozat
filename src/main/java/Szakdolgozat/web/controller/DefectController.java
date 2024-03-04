package Szakdolgozat.web.controller;

import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.repository.DefectRepository;
import Szakdolgozat.service.DefectService;
import Szakdolgozat.web.dto.DefectDto;
import Szakdolgozat.web.model.CreateDefectRequest;
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
        var x = defectService.addDefect(createDefectRequest);
        DefectDto defectDto = DefectDto.builder()
                .id(x.getId())
                .name(x.getName())
                .description(x.getDescrition())
                .build();
        return ResponseEntity.ok(defectDto);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public @ResponseBody Iterable<DefectEntity> findAllDefects() {
        return defectRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/name/{defectName}")
    public ResponseEntity<List<DefectEntity>> findDefectByName(@PathVariable(value = "defectName") String name) {
        return new ResponseEntity<>(defectRepository.findByNameContainingIgnoreCase(name), HttpStatus.OK);
    }
}