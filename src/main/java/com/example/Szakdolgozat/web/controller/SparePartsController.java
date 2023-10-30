package com.example.Szakdolgozat.web.controller;

import com.example.Szakdolgozat.entities.SparePartsEntity;
import com.example.Szakdolgozat.repository.SparePartsRepository;
import com.example.Szakdolgozat.service.SparePartsService;
import com.example.Szakdolgozat.web.model.CreateSparePartsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/spare/")

public class SparePartsController {

    private final SparePartsRepository sparePartsRepository;

    private final SparePartsService sparePartsService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("all")
    public Iterable<SparePartsEntity> findAll(){return sparePartsRepository.findAll(); }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public SparePartsEntity addSpareParts(@RequestBody CreateSparePartsRequest createSparePartsRequest) throws Exception {
        return sparePartsService.addSparePart(createSparePartsRequest);
    }




}


