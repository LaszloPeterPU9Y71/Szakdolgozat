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
        ToolEntity x = toolService.addTool(createToolRequest);
        ToolDto toolDto = ToolDto.builder()
                .status(x.getStatus())
                .dateOfReceiving(x.getDateOfReceiving())
                .id(x.getId())
                .itemNumber(x.getItemNumber())
                .name(x.getName())
                .serialNumber(x.getSerialNumber())
                .typeNumber(x.getTypeNumber())
                .build();
        return ResponseEntity.ok(toolDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public Iterable<ToolEntity> findAllTools(){
        return toolRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/status={status}")
    public ResponseEntity<List<ToolEntity>> findByStatus(@PathVariable ("status") String status){
        return new ResponseEntity<>(toolRepository.findByStatusContainingIgnoreCase(status), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/name={toolName}")
    public ResponseEntity<List<ToolEntity>> findToolByName(@PathVariable (value = "toolName") String name){
        return new ResponseEntity<>(toolRepository.findByNameContainingIgnoreCase(name), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/item={itemNumber}")
    public ResponseEntity<List<ToolEntity>> findToolItemNumber(@PathVariable (value = "itemNumber") String itemNumber){
        return new ResponseEntity<>(toolRepository.findByItemNumberContainingIgnoreCase(itemNumber), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/type={typeNumber}")
    public ResponseEntity<List<ToolEntity>> findToolByItemNumber(@PathVariable ("typeNumber") String typeNumber){
        return new ResponseEntity<>(toolRepository.findByTypeNumberContainingIgnoreCase(typeNumber), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/serial={serialNumber}")
    public ResponseEntity<List<ToolEntity>> findToolBySerialNumber(@PathVariable ("serialNumber") String serialNumber){
        return new ResponseEntity<>(toolRepository.findBySerialNumberContainingIgnoreCase(serialNumber), HttpStatus.OK);
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
