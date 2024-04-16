package Szakdolgozat.web.controller;

import Szakdolgozat.service.SparePartsService;
import Szakdolgozat.web.dto.SparePartsDto;
import Szakdolgozat.web.model.CreateSparePartsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/spare/")

public class SparePartsController {


    private final SparePartsService sparePartsService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("all")
    public ResponseEntity<List<SparePartsDto>> findAllSpareParts(){
        List<SparePartsDto> sparePartsDtos = sparePartsService.findAllSpareParts();
        return ResponseEntity.ok(sparePartsDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("find-by-name/{name}")
    public ResponseEntity<List<SparePartsDto>> findSparePartsByName(@PathVariable("name") String name){
        List<SparePartsDto> sparePartsDtos = sparePartsService.findSparePartsByName(name);
        return ResponseEntity.ok(sparePartsDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("find-by-number/{number}")
    public ResponseEntity<List<SparePartsDto>> findSparePartsByNumber(@PathVariable("number") String number){
        List<SparePartsDto> sparePartsDtos = sparePartsService.findSparePartsByNumber(number);
        return ResponseEntity.ok(sparePartsDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public ResponseEntity<SparePartsDto> addSpareParts(@RequestBody CreateSparePartsRequest createSparePartsRequest) throws Exception {
        SparePartsDto sparePartsDto = sparePartsService.addSparePart(createSparePartsRequest);
        return ResponseEntity.ok(sparePartsDto);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/update/{partNumber}")
    public String updateSparePartsData(@PathVariable("partNumber") String number,
                                 @RequestBody CreateSparePartsRequest createSparePartsRequest) {
        sparePartsService.updateSparePartData(number, createSparePartsRequest);
        return "Az alkatrész adatai frissültek!";
    }




}


