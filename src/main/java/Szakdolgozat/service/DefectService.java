package Szakdolgozat.service;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.ConstraintViolationException;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.repository.DefectRepository;
import Szakdolgozat.service.mapper.DefectMapper;
import Szakdolgozat.service.mapper.entityToDto.DefectMapStructDto;
import Szakdolgozat.web.dto.DefectDto;
import Szakdolgozat.web.model.CreateDefectRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor


public class DefectService {

    private final DefectRepository defectRepository;
    private final DefectMapper defectMapper;
    private final DefectMapStructDto defectMapStructDto;
    public DefectDto addDefect(CreateDefectRequest createDefectRequest) throws ConstraintViolationException {
        String name = createDefectRequest.getName();
        Optional<DefectEntity> mayBeName = defectRepository.findByName(name);

        if (mayBeName.isPresent()) {
            throw new ConstraintViolationException(String.format("A hiba megnevezése már létezik: '%s'", name));
        }
        DefectEntity defect = defectMapper.map(createDefectRequest);
        DefectEntity defectEntity = defectRepository.save(defect);
        return defectMapStructDto.fromEntitytoDto(defectEntity);
    }

    public List<DefectDto> findAllDefects(){
        Iterable<DefectEntity> defectEntities = defectRepository.findAll();
        return defectMapStructDto.fromEntitytoDtoList(defectEntities);
    }

    public List<DefectDto> findDefectsByName(String name) throws DataNotFoundException {
        Iterable<DefectEntity> defectEntities = defectRepository.findByNameContainingIgnoreCase(name);
        if(defectRepository.findByNameContainingIgnoreCase(name).isEmpty()){
            throw new DataNotFoundException(String.format("Ezzel a megnevezéssel nem található hiba: %s !", name));
        }
        return defectMapStructDto.fromEntitytoDtoList(defectEntities);
    }
}
