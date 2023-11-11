package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OwnerCompanyEmployeeRepository extends CrudRepository<OwnerCompanyEmloyeeEntity, Long> {


    Set<OwnerCompanyEmloyeeEntity> findByNameContainingIgnoreCase(String name);

    Optional<OwnerCompanyEmloyeeEntity> findByEmail(String email);

    OwnerCompanyEmloyeeEntity findById(long id);
}
