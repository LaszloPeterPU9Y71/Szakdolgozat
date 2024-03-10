package Szakdolgozat.service;

import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.AlreadyCreatedException;
import Szakdolgozat.repository.DefectRepository;
import Szakdolgozat.service.mapper.DefectMapper;
import Szakdolgozat.web.model.CreateDefectRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor


public class DefectService {

    private final DefectRepository defectRepository;
    private final DefectMapper defectMapper;

    public DefectEntity addDefect(CreateDefectRequest createDefectRequest) throws AlreadyCreatedException {
        String name = createDefectRequest.getName();
        Optional<DefectEntity> mayBeName = defectRepository.findByName(name);

        if (mayBeName.isPresent()) {
            throw new AlreadyCreatedException(String.format("A hiba megnevezése már létezik: '%s'", name));
        }
        DefectEntity defect = defectMapper.map(createDefectRequest);

        return defectRepository.save(defect);
    }
}
