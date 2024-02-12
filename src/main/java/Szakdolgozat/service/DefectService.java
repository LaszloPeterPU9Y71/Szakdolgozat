package Szakdolgozat.service;
import Szakdolgozat.service.mapper.DefectMapper;
import Szakdolgozat.web.model.CreateDefectRequest;
import Szakdolgozat.entities.DefectEntity;
import Szakdolgozat.repository.DefectRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DefectService {
    private final DefectRepository defectRepository;
    private final DefectMapper defectMapper;



    public DefectEntity addDefect(CreateDefectRequest createDefectRequest) throws Exception {
        String name = createDefectRequest.getName();
        Optional<DefectEntity> mayBeName = defectRepository.findByName(name);
        if (mayBeName.isPresent()) {
            throw new ValidationException(String.format("A hiba megnevezése már létezik: '%s'", name));
        }
        DefectEntity defect = defectMapper.map(createDefectRequest);

        return defectRepository.save(defect);
    }
}
