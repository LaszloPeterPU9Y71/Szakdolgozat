package com.example.Szakdolgozat.web.controller;

import com.example.Szakdolgozat.entities.ToolEntity;
import com.example.Szakdolgozat.repository.ToolRepository;
import com.example.Szakdolgozat.service.ToolService;
import com.example.Szakdolgozat.web.model.CreateToolRequest;
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

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public ToolEntity createTool(@RequestBody CreateToolRequest createToolRequest) throws Exception {
        return toolService.addTool(createToolRequest);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public Iterable<ToolEntity> findAllTools(){
        return toolRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/status={status}")
    public ResponseEntity<List<ToolEntity>> findByStatus(@PathVariable ("status") String status){
        return new ResponseEntity<>(toolRepository.findByStatus(status), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/name={toolName}")
    public ResponseEntity<List<ToolEntity>> findToolByName(@PathVariable (value = "toolName") String name){
        return new ResponseEntity<>(toolRepository.findByName(name), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/item={itemNumber}")
    public ResponseEntity<List<ToolEntity>> findToolItemNumber(@PathVariable (value = "itemNumber") String itemNumber){
        return new ResponseEntity<>(toolRepository.findByItemNumber(itemNumber), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/type={typeNumber}")
    public ResponseEntity<List<ToolEntity>> findToolByItemNumber(@PathVariable ("typeNumber") String typeNumber){
        return new ResponseEntity<>(toolRepository.findByTypeNumber(typeNumber), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/serial={serialNumber}")
    public ResponseEntity<List<ToolEntity>> findToolBySerialNumber(@PathVariable ("serialNumber") String serialNumber){
        return new ResponseEntity<>(toolRepository.findBySerialNumber(serialNumber), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/update/{id}")
    public String updateToolData(@PathVariable("id") long id,
                                 @RequestBody CreateToolRequest createToolRequest) {
        toolService.updateToolData(id, createToolRequest);
        return "Tool data updated!";
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/delete/{id}")
    public String deleteTool(@PathVariable("id") long id) {
        toolService.deleteTool(id);
        return "The tool with id: " + id + " has been deleted";
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/updateStatus={id}")
    public String updateToolStatus(@PathVariable("id") long id,
                                 @RequestBody CreateToolRequest createToolRequest) {
        toolService.updateToolStatus(id, createToolRequest);
        return "Tool Status updated!";
    }


}
