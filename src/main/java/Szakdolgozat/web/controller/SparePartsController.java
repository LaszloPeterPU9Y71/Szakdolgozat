package Szakdolgozat.web.controller;

import Szakdolgozat.service.SparePartsService;
import Szakdolgozat.web.model.CreateSparePartsRequest;
import Szakdolgozat.entities.SparePartsEntity;
import Szakdolgozat.repository.SparePartsRepository;
import lombok.RequiredArgsConstructor;
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


