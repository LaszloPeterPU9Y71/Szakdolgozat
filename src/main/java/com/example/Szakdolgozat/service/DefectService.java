package com.example.Szakdolgozat.service;
import com.example.Szakdolgozat.entities.DefectEntity;
import com.example.Szakdolgozat.repository.DefectRepository;
import com.example.Szakdolgozat.service.mapper.DefectMapper;
import com.example.Szakdolgozat.web.model.CreateDefectRequest;
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

            throw new Exception(String.format("Defect name  already exists: '%s'", name));
        }

        DefectEntity defect = defectMapper.map(createDefectRequest);

        return defectRepository.save(defect);
    }
}
