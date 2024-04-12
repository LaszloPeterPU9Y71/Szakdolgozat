package Szakdolgozat.web.controller;

import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.entities.ToolEntity;
import Szakdolgozat.repository.ToolRepository;
import Szakdolgozat.service.ToolService;
import Szakdolgozat.web.dto.ToolDto;
import Szakdolgozat.web.model.CreateToolRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tools")
@RequiredArgsConstructor
public class ToolController {

    private final ToolService toolService;
    private final ToolRepository toolRepository;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public ResponseEntity<ToolDto> createTool(@RequestBody  CreateToolRequest createToolRequest) {
        ToolDto toolDto = toolService.addTool(createToolRequest);
        return ResponseEntity.ok(toolDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<ToolDto>> findAllTools(){
        List<ToolDto> toolDtos = toolService.findAllTools();
        return ResponseEntity.ok(toolDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ToolDto>> findByStatus(@PathVariable ("status") String status){
        List<ToolDto> toolDtos = toolService.findByStatus(status);
        return ResponseEntity.ok(toolDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/name/{toolName}")
    public ResponseEntity<List<ToolDto>> findToolByName(@PathVariable (value = "toolName") String name){
        List<ToolDto> toolDtos = toolService.findByName(name);
        return ResponseEntity.ok(toolDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/id/{id}")
    public ResponseEntity<ToolDto> findToolById(@PathVariable (value = "id") long id){
        ToolDto toolDtos = toolService.findById(id);
        return ResponseEntity.ok(toolDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/item/{itemNumber}")
    public ResponseEntity<List<ToolDto>> findToolByItemNumber(@PathVariable (value = "itemNumber") String itemNumber){
        List<ToolDto> toolDtos = toolService.findByItemNumber(itemNumber);
        return ResponseEntity.ok(toolDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/type/{typeNumber}")
    public ResponseEntity<List<ToolDto>> findToolByTypeNumber(@PathVariable ("typeNumber") String typeNumber){
        List<ToolDto> toolDtos = toolService.findByTypeNumber(typeNumber);
        return ResponseEntity.ok(toolDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<List<ToolDto>> findToolBySerialNumber(@PathVariable ("serialNumber") String serialNumber){
        List<ToolDto> toolDtos = toolService.findBySerialNumber(serialNumber);
        return ResponseEntity.ok(toolDtos);
    }



   /* @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/update/{id}")
    public String updateToolData(@PathVariable("id") long id,
                                 @RequestBody CreateToolRequest createToolRequest) {

        toolService.updateToolData(id, createToolRequest);
        return "A gép adatai frissültek!";
    }*/
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/delete/{id}")
    public String deleteTool(@PathVariable("id") long id) {
        toolService.deleteTool(id);
        return "A : " + id + " azonosítójú gép törlése sikeres.";
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("/update-status/{id}")
    public String updateToolStatus(@PathVariable("id") long id,
                                 @RequestBody CreateToolRequest createToolRequest) {
        toolService.updateToolStatus(id, createToolRequest);
        return "A gép státusza megváltozott!";
    }


}
