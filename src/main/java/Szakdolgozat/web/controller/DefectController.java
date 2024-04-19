package Szakdolgozat.web.controller;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.repository.DefectRepository;
import Szakdolgozat.service.DefectService;
import Szakdolgozat.service.mapper.entityToDto.DefectMapStructDto;
import Szakdolgozat.web.dto.DefectDto;
import Szakdolgozat.web.model.CreateDefectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/defects")
@RequiredArgsConstructor

public class DefectController {

    private final DefectService defectService;
    private final DefectRepository defectRepository;
    private final DefectMapStructDto defectMapStructDto;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public ResponseEntity<DefectDto> createDefect(@RequestBody CreateDefectRequest createDefectRequest) throws Exception {
        DefectDto defectDto = defectService.addDefect(createDefectRequest);
        return ResponseEntity.ok(defectDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/find-by-id/{id}")
    public List<DefectDto> findById(@PathVariable(value = "id") List<Long> ids){
        List<DefectEntity> defectEntities = defectRepository.findAllByIdIsIn(ids);
        if(defectEntities.isEmpty()){
            throw new DataNotFoundException(String.format("Nem találtunk ilyen azonosítójú hibát: %s !", ids));

        }

        return defectMapStructDto.fromEntityToDtoList(defectEntities);
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