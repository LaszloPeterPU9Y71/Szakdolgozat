package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OwnerCompanyEmployeeRepository extends CrudRepository<OwnerCompanyEmloyeeEntity, Long> {


    Optional<OwnerCompanyEmloyeeEntity> findByEmail(String email);


}
