package com.example.Szakdolgozat.web.controller;

import com.example.Szakdolgozat.entities.SparePartsEntity;
import com.example.Szakdolgozat.entities.ToolEntity;
import com.example.Szakdolgozat.repository.SparePartsRepository;
import com.example.Szakdolgozat.service.SparePartsService;
import com.example.Szakdolgozat.web.model.CreateSparePartsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/spare/")

public class SparePartsController {

    private final SparePartsRepository sparePartsRepository;

    private final SparePartsService sparePartsService;


    @GetMapping("all")
    public Iterable<SparePartsEntity> findAll(){return sparePartsRepository.findAll(); }

    @PostMapping("add")
    public SparePartsEntity addSpareParts(@RequestBody CreateSparePartsRequest createSparePartsRequest) throws Exception {
        return sparePartsService.addSparePart(createSparePartsRequest);
    }

    @DeleteMapping("delete/{part}")
    public String deletePart(@PathVariable("part") String part){
        sparePartsService.deleteSparePart(part);
        return "Spare part with number: " + part + "has been deleted";
    }

    @PutMapping("/update/{part}")
    public String updateSparePartData(@PathVariable("part") String part,
                                      @RequestBody CreateSparePartsRequest createSparePartsRequest){
        sparePartsService.updateSparePartData(part, createSparePartsRequest);
                return "Spare part data updated";
        }


    @GetMapping("/type/{partNumber}")
    public ResponseEntity<Optional<SparePartsEntity>> findPartByNumber(@PathVariable ("partNumber") String partNumber){
        return new ResponseEntity<>(sparePartsRepository.findByPartNumber(partNumber), HttpStatus.OK);
    }






}


