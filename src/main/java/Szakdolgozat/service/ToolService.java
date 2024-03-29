package Szakdolgozat.service;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import Szakdolgozat.entities.ToolEntity;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import Szakdolgozat.repository.OwnerCompanyRepository;
import Szakdolgozat.repository.ToolRepository;
import Szakdolgozat.service.mapper.ToolMapper;
import Szakdolgozat.service.mapper.entityToDto.ToolMapStructDto;
import Szakdolgozat.web.dto.ToolDto;
import Szakdolgozat.web.model.CreateToolRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.tools.Tool;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ToolService {

    private final ToolRepository toolRepository;

    private final ToolMapper toolMapper;
    private final ToolMapStructDto toolMapStructDto;
    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;



    public List<ToolDto> findAllTools() throws DataNotFoundException{
        Iterable<ToolEntity> toolEntities = toolRepository.findAll();
        if(toolMapStructDto.fromEntityToDtoList(toolEntities).isEmpty()){
            throw new DataNotFoundException("Még nem vettek fel javítandó gépet az adatbázisba");
        }
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findByStatus(String status) throws DataNotFoundException{
        List<ToolEntity> toolEntities = toolRepository.findByStatusContainingIgnoreCase(status);
        if(toolMapStructDto.fromEntityToDtoList(toolEntities).isEmpty()){
            throw new DataNotFoundException("Nincs ilyen státuszú gép.");
        }
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findByName(String name) throws DataNotFoundException{
        List<ToolEntity> toolEntities = toolRepository.findByNameContainingIgnoreCase(name);
        if(toolMapStructDto.fromEntityToDtoList(toolEntities).isEmpty()){
            throw new DataNotFoundException(String.format("Nem találtunk ilyen megnevezésű gépet: %s !", name));
        }
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findByItemNumber(String itemNumber){
        List<ToolEntity> toolEntities = toolRepository.findByItemNumberContainingIgnoreCase(itemNumber);
        if(toolMapStructDto.fromEntityToDtoList(toolEntities).isEmpty()){
            throw new DataNotFoundException(String.format("Nem találtunk ilyen cikkszámú gépet: %s !", itemNumber));
        }
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findByTypeNumber(String typeNumber){
        List<ToolEntity> toolEntities = toolRepository.findByTypeNumberContainingIgnoreCase(typeNumber);
        if(toolMapStructDto.fromEntityToDtoList(toolEntities).isEmpty()){
            throw new DataNotFoundException(String.format("Nem találtunk ilyen típusszámú gépet: %s !", typeNumber));
        }
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public List<ToolDto> findBySerialNumber(String serialNumber){
        List<ToolEntity> toolEntities = toolRepository.findBySerialNumberContainingIgnoreCase(serialNumber);
        if(toolMapStructDto.fromEntityToDtoList(toolEntities).isEmpty()){
            throw new DataNotFoundException(String.format("Nem találtunk ilyen gyártási számú gépet: %s !", serialNumber));
        }
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public ToolDto addTool(CreateToolRequest createToolRequest){
        ToolEntity tool = toolMapper.map(createToolRequest);
        tool.setDateOfReceiving(LocalDateTime.now());
        tool.setStatus("Beérkezett");
        ToolEntity toolEntity = toolRepository.save(tool);
        return toolMapStructDto.fromEntityToDto(toolEntity);
    }

    public void updateToolData(long id, CreateToolRequest createToolRequest) throws DataNotFoundException {
        Optional<ToolEntity> maybeToolEntity = toolRepository.findById(id);
        List<ToolEntity> maybeToolSerialNumber = toolRepository.findBySerialNumberContainingIgnoreCase(createToolRequest.getSerialNumber());

        if(maybeToolEntity.isEmpty()){
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel az azonosítóval:  %s", id));
        } else if(maybeToolSerialNumber.isEmpty()){
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel a gyártási számmal %s", createToolRequest.getSerialNumber()));
        }
        toolRepository.save(updateToolData(maybeToolEntity.get(), createToolRequest));
    }

    private ToolEntity updateToolData(ToolEntity current, CreateToolRequest createToolRequest){
        current.setName(createToolRequest.getName());
        current.setTypeNumber(createToolRequest.getTypeNumber());
        current.setItemNumber(createToolRequest.getItemNumber());
        current.setSerialNumber(createToolRequest.getSerialNumber());
        current.setStatus(createToolRequest.getStatus());
        current.setDescription((createToolRequest.getDescription()));
        return current;
    }

    public void deleteTool(long id) throws DataNotFoundException {
        if (!toolRepository.existsById(id)) {
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel az azonosítóval: %s", id));
        } else {
            toolRepository.deleteById(id);
        }
    }

    public void updateToolStatus(long id, CreateToolRequest createToolRequest) throws DataNotFoundException{
        Optional<ToolEntity> maybeToolEntity = toolRepository.findById(id);
        List<ToolEntity> maybeToolSerialNumber = toolRepository.findBySerialNumberContainingIgnoreCase(createToolRequest.getSerialNumber());

        if(maybeToolEntity.isEmpty()){
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel az azonosítóval: %s", id));
        } else if(maybeToolSerialNumber.isEmpty()){
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("Nem található gép ezzel a gyártási számmal %s", createToolRequest.getSerialNumber()));
        }
        toolRepository.save(updateToolStatus(maybeToolEntity.get(), createToolRequest));
    }

    public ToolEntity updateToolStatus(ToolEntity current, CreateToolRequest createToolRequest) {
        current.setStatus(createToolRequest.getStatus());
        return current;

    }


}
