package Szakdolgozat.repository;

import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface OwnerCompanyEmployeeRepository extends CrudRepository<OwnerCompanyEmployeeEntity, Long> {


    Iterable<OwnerCompanyEmployeeEntity> findByNameContainingIgnoreCase(String name);

    Optional<OwnerCompanyEmployeeEntity> findByEmail(String email);

    OwnerCompanyEmployeeEntity findById(long id);



}
