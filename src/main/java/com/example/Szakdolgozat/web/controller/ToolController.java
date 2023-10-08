package com.example.Szakdolgozat.web.controller;

import com.example.Szakdolgozat.entities.ToolEntity;
import com.example.Szakdolgozat.repository.ToolRepository;
import com.example.Szakdolgozat.service.ToolService;
import com.example.Szakdolgozat.web.model.CreateToolRequest;
import com.example.Szakdolgozat.web.model.CreateUserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tools")
@RequiredArgsConstructor

public class ToolController {

    private final ToolRepository toolRepository;
    private final ToolService toolService;

    @PostMapping("/create")
    public ToolEntity createTool(@RequestBody CreateToolRequest createToolRequest) throws Exception {
        return toolService.addTool(createToolRequest);
    }

    @GetMapping("/all")
    public Iterable<ToolEntity> findAllTools(){
        return toolRepository.findAll();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ToolEntity>> findByStatus(@PathVariable ("status") String status){
        return new ResponseEntity<>(toolRepository.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/name/{toolName}")
    public ResponseEntity<List<ToolEntity>> findToolByName(@PathVariable ("toolName") String name){
        return new ResponseEntity<>(toolRepository.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/type/{typeNumber}")
    public ResponseEntity<List<ToolEntity>> findToolByItemNumber(@PathVariable ("typeNumber") String typeNumber){
        return new ResponseEntity<>(toolRepository.findByTypeNumber(typeNumber), HttpStatus.OK);
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<ToolEntity>> findToolBySerialNumber(@PathVariable ("serialNumber") String serialNumber){
        return new ResponseEntity<>(toolRepository.findBySerialNumber(serialNumber), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public String updateToolData(@PathVariable("id") long id,
                                 @RequestBody CreateToolRequest createToolRequest) {
        toolService.updateToolData(id, createToolRequest);
        return "Tool data updated!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTool(@PathVariable("id") long id) {
        toolService.deleteTool(id);
        return "The tool with id: " + id + " has been deleted";
    }

    @PutMapping("/updateStatus/{id}")
    public String updateToolStatus(@PathVariable("id") long id,
                                 @RequestBody CreateToolRequest createToolRequest) {
        toolService.updateToolStatus(id, createToolRequest);
        return "Tool Status updated!";
    }


}
