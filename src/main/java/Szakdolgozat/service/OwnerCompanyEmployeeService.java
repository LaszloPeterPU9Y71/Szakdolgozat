package Szakdolgozat.service;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.service.mapper.entityToDto.OwnerCompanyEmployeeMapStructDto;
import Szakdolgozat.service.mapper.entityToDto.OwnerCompanyMapStructDto;
import Szakdolgozat.web.dto.OwnerCompanyDto;
import Szakdolgozat.web.dto.OwnerCompanyEmployeeDto;
import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import Szakdolgozat.repository.OwnerCompanyRepository;
import Szakdolgozat.service.mapper.OwnerCompanyEmployeeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerCompanyEmployeeService {

    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;
    private final OwnerCompanyEmployeeMapper ownerCompanyEmployeeMapper;
    private final OwnerCompanyEmployeeMapStructDto ownerCompanyEmployeeMapStructDto;



    public OwnerCompanyEmployeeDto addEmployee(CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws Exception{
        String email = createOwnerCompanyEmployeeRequest.getEmail();
        Optional<OwnerCompanyEmployeeEntity> maybeEmployee = ownerCompanyEmployeeRepository.findByEmail(email);

        if(maybeEmployee.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("The employee exists with: %s email address", email));
        }

        OwnerCompanyEmployeeEntity employee = ownerCompanyEmployeeMapper.map(createOwnerCompanyEmployeeRequest);
        employee.setStatus(true);
        OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity = ownerCompanyEmployeeRepository.save(employee);
        return ownerCompanyEmployeeMapStructDto.fromEntityToDto(ownerCompanyEmployeeEntity);

    }

    public List<OwnerCompanyEmployeeDto> findAllOwnerCompany(){
        Iterable<OwnerCompanyEmployeeEntity> ownerCompanyEmployeeEntities = ownerCompanyEmployeeRepository.findAll();
        return ownerCompanyEmployeeMapStructDto.fromEntityToDtoList(ownerCompanyEmployeeEntities);
    }

    public List<OwnerCompanyEmployeeDto> findOwnerCompanyByName(String name){
        Iterable<OwnerCompanyEmployeeEntity> ownerCompanyEmployeeEntities = ownerCompanyEmployeeRepository.findByNameContainingIgnoreCase(name);
        return ownerCompanyEmployeeMapStructDto.fromEntityToDtoList(ownerCompanyEmployeeEntities);
    }





}
