package Szakdolgozat.service;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorksheetService {

    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;

    public Iterable<OwnerCompanyEmployeeEntity> worksheetUserAndCompanyData(String name ){
        return ownerCompanyEmployeeRepository.findByNameContainingIgnoreCase(name);
    }

}
