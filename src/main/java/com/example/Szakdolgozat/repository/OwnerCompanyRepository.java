package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.domain.OwnerCompanyEntity;
import org.springframework.data.repository.CrudRepository;

public interface OwnerCompanyRepository extends CrudRepository<OwnerCompanyEntity, Long> {

    Iterable<OwnerCompanyEntity> findByName(String name);
}
