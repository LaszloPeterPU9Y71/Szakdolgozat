package Szakdolgozat.service;

import Szakdolgozat.service.mapper.ToolMapper;
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



    public ToolEntity addTool(CreateToolRequest createToolRequest) throws Exception {
        ToolEntity tool = toolMapper.map(createToolRequest);

        tool.setDateOfReceiving(LocalDateTime.now());
        tool.setStatus("Beérkezett");
        tool.setOwnerCompanyEmloyeeEntity(ownerCompanyEmployeeRepository.findById(createToolRequest.getOwnerCompanyEmployeeId()));
        tool.setDefects(defectRepository.findById(createToolRequest.getDefectsId()));
        return toolRepository.save(tool);
    }

    public void updateToolData(long id, CreateToolRequest createToolRequest){
        Optional<ToolEntity> maybeToolEntity = toolRepository.findById(id);
        List<ToolEntity> maybeToolSerialNumber = toolRepository.findBySerialNumberContainingIgnoreCase(createToolRequest.getSerialNumber());

        if(maybeToolEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id %s", id));
        } else if(maybeToolSerialNumber.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with serial number %s", createToolRequest.getSerialNumber()));
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Tool not found with id: %s", id));
        } else {
            toolRepository.deleteById(id);
        }
    }

    public void updateToolStatus(long id, CreateToolRequest createToolRequest){
        Optional<ToolEntity> maybeToolEntity = toolRepository.findById(id);
        List<ToolEntity> maybeToolSerialNumber = toolRepository.findBySerialNumberContainingIgnoreCase(createToolRequest.getSerialNumber());

        if(maybeToolEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Tool not found with id %s", id));
        } else if(maybeToolSerialNumber.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Tool not found with serial number %s", createToolRequest.getSerialNumber()));
        }
        toolRepository.save(updateToolStatus(maybeToolEntity.get(), createToolRequest));
    }

    public ToolEntity updateToolStatus(ToolEntity current, CreateToolRequest createToolRequest) {
        current.setStatus(createToolRequest.getStatus());
        return current;

    }


}