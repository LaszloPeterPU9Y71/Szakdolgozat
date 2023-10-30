package com.example.Szakdolgozat.web.controller;

import com.example.Szakdolgozat.entities.DefectEntity;
import com.example.Szakdolgozat.entities.UserEntity;
import com.example.Szakdolgozat.repository.DefectRepository;

import com.example.Szakdolgozat.service.DefectService;
import com.example.Szakdolgozat.web.model.CreateDefectRequest;
import com.example.Szakdolgozat.web.model.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/defects")
@RequiredArgsConstructor

public class DefectController {

    private final DefectService defectService;
    private final DefectRepository defectRepository;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public DefectEntity createDefect(@RequestBody CreateDefectRequest createDefectRequest) throws Exception {

        return defectService.addDefect(createDefectRequest);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public @ResponseBody Iterable<DefectEntity> findAllDefects(){
        return defectRepository.findAll();
    }

}
