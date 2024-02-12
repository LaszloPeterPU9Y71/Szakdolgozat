package Szakdolgozat.repository;

import Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface OwnerCompanyEmployeeRepository extends CrudRepository<OwnerCompanyEmloyeeEntity, Long> {


    Set<OwnerCompanyEmloyeeEntity> findByOwnerCompanyEntityIdAndNameContainingIgnoreCase(Long Id, String name);
    Set<OwnerCompanyEmloyeeEntity> findByNameContainingIgnoreCase(String name);

    Optional<OwnerCompanyEmloyeeEntity> findByEmail(String email);

    OwnerCompanyEmloyeeEntity findById(long id);



}
