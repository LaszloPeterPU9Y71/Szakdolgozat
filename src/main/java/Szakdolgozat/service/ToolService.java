package Szakdolgozat.service;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.entities.SparePartsEntity;
import Szakdolgozat.entities.ToolEntity;
import Szakdolgozat.repository.DefectRepository;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import Szakdolgozat.repository.SparePartsRepository;
import Szakdolgozat.repository.ToolRepository;
import Szakdolgozat.service.mapper.ToolMapper;
import Szakdolgozat.service.mapper.entityToDto.DefectMapStructDto;
import Szakdolgozat.service.mapper.entityToDto.ToolMapStructDto;
import Szakdolgozat.web.dto.ToolDto;
import Szakdolgozat.web.model.CreateToolRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service

@RequiredArgsConstructor
public class ToolService {

    private final ToolRepository toolRepository;
    private final ToolMapper toolMapper;
    private final ToolMapStructDto toolMapStructDto;
    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;
    private final EmailService emailService;
    private final DefectRepository defectRepository;
    private final SparePartsRepository sparePartsRepository;



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

    public ToolDto findById(long id){
        ToolEntity toolEntity = toolRepository.findByIdEquals(id);
        if(toolMapStructDto.fromEntityToDto(toolEntity) == null){
            throw new DataNotFoundException(String.format("Nem találtunk ilyen azonosítójú gépet: %s !", id));
        }
        return toolMapStructDto.fromEntityToDto(toolEntity);
    }

    public List<ToolDto> findByIdentifier(String identifier) {
        List<ToolEntity> toolEntities = toolRepository.findByIdentifierContainingIgnoreCase(identifier);
        if(toolEntities.isEmpty()){
            throw new DataNotFoundException(String.format("Nem találtunk ilyen azonosítójú gépet: %s !", identifier));
        }
        return toolMapStructDto.fromEntityToDtoList(toolEntities);
    }

    public ToolDto addTool(CreateToolRequest createToolRequest){



        String identifier = getIdentifier();
        List<DefectEntity> defectEntities = defectRepository.findAllByIdIsIn(createToolRequest.getDefects());
        OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity = ownerCompanyEmployeeRepository.findById(createToolRequest.getEmployeeId());
        ToolEntity tool = toolMapper.map(ownerCompanyEmployeeEntity, defectEntities, createToolRequest, identifier);
        ToolEntity toolEntity = toolRepository.save(tool);
        return toolMapStructDto.fromEntityToDto(toolEntity);
    }

    private String getIdentifier() {
        var count = toolRepository.getItemCountInMonth(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue());

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        String formattedYear = String.format("%02d", year % 100);
        String formattedMonth = String.format("%02d", month);

        return formattedYear + formattedMonth + "-" + (String.format("%02d", count + 1));
    }



    public void updateToolData(long id, CreateToolRequest createToolRequest) throws DataNotFoundException {
        Optional<ToolEntity> maybeToolEntity = toolRepository.findById(id);
        List<SparePartsEntity> sparePartEntities = (List<SparePartsEntity>) sparePartsRepository.findAllById(createToolRequest.getSpareParts().keySet());
        List<DefectEntity> defectEntities = defectRepository.findAllByIdIsIn(createToolRequest.getDefects());
        toolRepository.save(updateToolData(maybeToolEntity.get(), createToolRequest, sparePartEntities, defectEntities));

        Map<Long, Integer> result = new HashMap<>();
        for (SparePartsEntity actualSparePart : sparePartEntities) {
            if (result.containsKey(actualSparePart.getId())) {
                int currentAmount = result.get(actualSparePart.getId());
                result.put(actualSparePart.getId(), currentAmount+1);
            } else {
                result.put(actualSparePart.getId(), 1);
            }
        }
        System.out.println();
    }
    private ToolEntity updateToolData(ToolEntity current, CreateToolRequest createToolRequest, List<SparePartsEntity> sparePartsEntities, List<DefectEntity> defectEntities ){
        current.setDescription(createToolRequest.getDescription());
        current.setStatus(createToolRequest.getStatus());
        current.setSpareparts(sparePartsEntities);
        current.setIsInvoice(createToolRequest.getIsInvoice());
        current.setIsWarranty(createToolRequest.getIsWarranty());
        current.setIsRegistration(createToolRequest.getIsRegistration());
        current.setIsWarrantyTicket(createToolRequest.getIsWarrantyTicket());
        current.setDefects(defectEntities);

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ToolEntity maybeToolEntity = toolRepository.findByIdEquals(id);
        maybeToolEntity.setDefects(defectRepository.findAllByIdIsIn(createToolRequest.getDefects()));
        maybeToolEntity.setDateOfReceiving(LocalDateTime.parse(createToolRequest.getDateOfReceiving(), formatter));
        toolRepository.save(updateToolStatus(maybeToolEntity, createToolRequest));
        emailService.sendStatusChangedMail(maybeToolEntity.getOwnerCompanyEmployeeEntity().getEmail(), toolMapStructDto.fromEntityToDto(maybeToolEntity));
    }

    public ToolEntity updateToolStatus(ToolEntity current, CreateToolRequest createToolRequest) {
        current.setStatus(createToolRequest.getStatus());
        return current;

    }

}
