package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.OwnerCompanyEmloyeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface OwnerCompanyEmployeeRepository extends CrudRepository<OwnerCompanyEmloyeeEntity, Long> {

    Iterable<OwnerCompanyEmloyeeEntity> findByName(String name);

}
