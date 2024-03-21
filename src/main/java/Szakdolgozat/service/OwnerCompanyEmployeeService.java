package Szakdolgozat.service;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.ConstraintViolationException;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.service.mapper.entityToDto.OwnerCompanyEmployeeMapStructDto;
import Szakdolgozat.web.dto.OwnerCompanyEmployeeDto;
import Szakdolgozat.web.model.CreateOwnerCompanyEmployeeRequest;
import Szakdolgozat.repository.OwnerCompanyEmployeeRepository;
import Szakdolgozat.repository.OwnerCompanyRepository;
import Szakdolgozat.service.mapper.OwnerCompanyEmployeeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OwnerCompanyEmployeeService {

    private final OwnerCompanyEmployeeRepository ownerCompanyEmployeeRepository;
    private final OwnerCompanyEmployeeMapper ownerCompanyEmployeeMapper;
    private final OwnerCompanyEmployeeMapStructDto ownerCompanyEmployeeMapStructDto;
    private final OwnerCompanyRepository ownerCompanyRepository;




    public OwnerCompanyEmployeeDto addEmployee(CreateOwnerCompanyEmployeeRequest createOwnerCompanyEmployeeRequest) throws ConstraintViolationException {
        String email = createOwnerCompanyEmployeeRequest.getEmail();
        Optional<OwnerCompanyEmployeeEntity> maybeEmployee = ownerCompanyEmployeeRepository.findByEmail(email);

        if(maybeEmployee.isPresent()){
            throw new ConstraintViolationException(String.format("Ezzel az e-mail címmel már regiszráltak ügyfelet: %s !", email));
        }

        OwnerCompanyEmployeeEntity employee = ownerCompanyEmployeeMapper.map(createOwnerCompanyEmployeeRequest);
        employee.setOwnerCompanyEntity(null);
        employee.setStatus(true);
        OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity = ownerCompanyEmployeeRepository.save(employee);
        return ownerCompanyEmployeeMapStructDto.fromEntityToDto(ownerCompanyEmployeeEntity);

    }

    public List<OwnerCompanyEmployeeDto> findAllOwnerCompanyEmployee() throws DataNotFoundException {
        Iterable<OwnerCompanyEmployeeEntity> ownerCompanyEmployeeEntities = ownerCompanyEmployeeRepository.findAll();
            if(ownerCompanyEmployeeMapStructDto.fromEntityToDtoList(ownerCompanyEmployeeEntities).isEmpty()){
                throw new DataNotFoundException("Az adatbázis üres");
            }
        return ownerCompanyEmployeeMapStructDto.fromEntityToDtoList(ownerCompanyEmployeeEntities);
    }

    public List<OwnerCompanyEmployeeDto> findOwnerCompanyEmployeeByName(String name) throws DataNotFoundException{
        Iterable<OwnerCompanyEmployeeEntity> ownerCompanyEmployeeEntities = ownerCompanyEmployeeRepository.findByNameContainingIgnoreCase(name);
            if(ownerCompanyEmployeeMapStructDto.fromEntityToDtoList(ownerCompanyEmployeeEntities).isEmpty()){
                throw new DataNotFoundException("Ilyen nevű partner cég nincs az adatbázisban");
        }
        return ownerCompanyEmployeeMapStructDto.fromEntityToDtoList(ownerCompanyEmployeeEntities);
    }


    public OwnerCompanyEmployeeEntity findOwnerCompanyEmployeeById(long companyId) throws DataNotFoundException{
            if(ownerCompanyEmployeeRepository.findById(companyId) == null){
                throw new DataNotFoundException(String.format("Ezzel az azonosítóval nem szerepel cég az adatbázisban: %s", companyId));
            }
    return ownerCompanyEmployeeRepository.findById(companyId);
    }


    public void companyAssignEmployee(long companyId, long employeeId) throws DataNotFoundException {
        OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity = ownerCompanyEmployeeRepository.findById(employeeId);
        if(ownerCompanyEmployeeEntity == null){
            throw new DataNotFoundException(String.format("Ezzel az azonosítóval nem található alkalmazott: %s", employeeId));
        }
        OwnerCompanyEntity ownerCompanyEntity = ownerCompanyRepository.findById(companyId);
        if(ownerCompanyEntity == null){
            throw new DataNotFoundException(String.format("Ezzel az azonosítóval nem található cég: %s", companyId ));

        }
        ownerCompanyEmployeeEntity.setOwnerCompanyEntity(ownerCompanyEntity);
    }

    public void companyUnassignEmployee(long employeeId) {
        OwnerCompanyEmployeeEntity ownerCompanyEmployeeEntity = ownerCompanyEmployeeRepository.findById(employeeId);
        if(ownerCompanyEmployeeEntity == null){
            throw new DataNotFoundException(String.format("Ezzel az azonosítóval nem található alkalmazott: %s", employeeId));
        }else if(ownerCompanyEmployeeEntity.getOwnerCompanyEntity()==null){
            throw new DataNotFoundException("Ehhez a személyhez nincs hozzárendelve cég");
        }else ownerCompanyEmployeeEntity.setOwnerCompanyEntity(null);
    }
}
