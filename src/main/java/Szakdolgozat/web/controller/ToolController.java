package Szakdolgozat.web.controller;

import Szakdolgozat.entities.ToolEntity;
import Szakdolgozat.repository.ToolRepository;
import Szakdolgozat.service.ToolService;
import Szakdolgozat.web.dto.ToolDto;
import Szakdolgozat.web.model.CreateToolRequest;
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
    public ResponseEntity<ToolDto> createTool(@RequestBody CreateToolRequest createToolRequest) throws Exception {
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
    @GetMapping("/status={status}")
    public ResponseEntity<List<ToolDto>> findByStatus(@PathVariable ("status") String status){
        List<ToolDto> toolDtos = toolService.findByStatus(status);
        return ResponseEntity.ok(toolDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/name={toolName}")
    public ResponseEntity<List<ToolDto>> findToolByName(@PathVariable (value = "toolName") String name){
        List<ToolDto> toolDtos = toolService.findByName(name);
        return ResponseEntity.ok(toolDtos);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/item={itemNumber}")
    public ResponseEntity<List<ToolDto>> findToolByItemNumber(@PathVariable (value = "itemNumber") String itemNumber){
        List<ToolDto> toolDtos = toolService.findByItemNumber(itemNumber);
        return ResponseEntity.ok(toolDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/type={typeNumber}")
    public ResponseEntity<List<ToolDto>> findToolByTypeNumber(@PathVariable ("typeNumber") String typeNumber){
        List<ToolDto> toolDtos = toolService.findByTypeNumber(typeNumber);
        return ResponseEntity.ok(toolDtos);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/serial={serialNumber}")
    public ResponseEntity<List<ToolDto>> findToolBySerialNumber(@PathVariable ("serialNumber") String serialNumber){
        List<ToolDto> toolDtos = toolService.findBySerialNumber(serialNumber);
        return ResponseEntity.ok(toolDtos);
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
