package Szakdolgozat.service;

import Szakdolgozat.service.mapper.ToolMapper;
import Szakdolgozat.service.mapper.entityToDto.ToolMapStructDto;
import Szakdolgozat.web.dto.ToolDto;
import Szakdolgozat.web.model.CreateToolRequest;
import Szakdolgozat.entities.ToolEntity;
import Szakdolgozat.repository.DefectRepository;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import Szakdolgozat.repository.ToolRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ToolService {

    private final ToolRepository toolRepository;

    private final ToolMapper toolMapper;
    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;
    private final DefectRepository defectRepository;
    private final ToolMapStructDto toolMapStructDto;



    public List<ToolDto> findAllTools(){
        Iterable<ToolEntity> toolEntities = toolRepository.findAll();
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findByStatus(String status){
        List<ToolEntity> toolEntities = toolRepository.findByStatusContainingIgnoreCase(status);
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findByName(String name){
        List<ToolEntity> toolEntities = toolRepository.findByNameContainingIgnoreCase(name);
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findByItemNumber(String itemNumber){
        List<ToolEntity> toolEntities = toolRepository.findByItemNumberContainingIgnoreCase(itemNumber);
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findByTypeNumber(String typeNumber){
        List<ToolEntity> toolEntities = toolRepository.findByTypeNumberContainingIgnoreCase(typeNumber);
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findBySerialNumber(String serialNumber){
        List<ToolEntity> toolEntities = toolRepository.findBySerialNumberContainingIgnoreCase(serialNumber);
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public ToolDto addTool(CreateToolRequest createToolRequest) throws Exception {
        ToolEntity tool = toolMapper.map(createToolRequest);

        tool.setDateOfReceiving(LocalDateTime.now());
        tool.setStatus("Beérkezett");
        tool.setOwnerCompanyEmployeeEntity(ownerCompanyEmployeeRepository.findById(createToolRequest.getOwnerCompanyEmployeeId()));
        tool.setDefects(defectRepository.findById(createToolRequest.getDefectsId()));
        ToolEntity toolEntity = toolRepository.save(tool);
        return toolMapStructDto.fromEntityToDto(toolEntity);
    }

    public void updateToolData(long id, CreateToolRequest createToolRequest){
        Optional<ToolEntity> maybeToolEntity = toolRepository.findById(id);
        List<ToolEntity> maybeToolSerialNumber = toolRepository.findBySerialNumberContainingIgnoreCase(createToolRequest.getSerialNumber());

        if(maybeToolEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel az azonosítóval:  %s", id));
        } else if(maybeToolSerialNumber.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel a gyártási számmal %s", createToolRequest.getSerialNumber()));
        }
        toolRepository.save(updateToolData(maybeToolEntity.get(), createToolRequest));
    }

    private ToolEntity updateToolData(ToolEntity current, CreateToolRequest createToolRequest) {
        current.setName(createToolRequest.getName());
        current.setTypeNumber(createToolRequest.getTypeNumber());
        current.setItemNumber(createToolRequest.getItemNumber());
        current.setSerialNumber(createToolRequest.getSerialNumber());
        current.setDateOfReceiving(LocalDateTime.now());
        current.setStatus(createToolRequest.getStatus());
        return current;
    }

    public void deleteTool(long id) {
        if (!toolRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel az azonosítóval: %s", id));
        } else {
            toolRepository.deleteById(id);
        }
    }

    public void updateToolStatus(long id, CreateToolRequest createToolRequest){
        Optional<ToolEntity> maybeToolEntity = toolRepository.findById(id);
        List<ToolEntity> maybeToolSerialNumber = toolRepository.findBySerialNumberContainingIgnoreCase(createToolRequest.getSerialNumber());

        if(maybeToolEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel az azonosítóval: %s", id));
        } else if(maybeToolSerialNumber.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel a gyártási számmal %s", createToolRequest.getSerialNumber()));
        }
        toolRepository.save(updateToolStatus(maybeToolEntity.get(), createToolRequest));
    }

    public ToolEntity updateToolStatus(ToolEntity current, CreateToolRequest createToolRequest) {
        current.setStatus(createToolRequest.getStatus());
        return current;

    }


}
