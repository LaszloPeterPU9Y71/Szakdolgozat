package Szakdolgozat.repository;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface OwnerCompanyEmployeeRepository extends CrudRepository<OwnerCompanyEmployeeEntity, Long> {


    Set<OwnerCompanyEmployeeEntity> findByOwnerCompanyEntityIdAndNameContainingIgnoreCase(Long Id, String name);
    Set<OwnerCompanyEmployeeEntity> findByNameContainingIgnoreCase(String name);

    Optional<OwnerCompanyEmployeeEntity> findByEmail(String email);

    OwnerCompanyEmployeeEntity findById(long id);



}
