package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.CompanyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {

    Iterable<CompanyEntity> findByName(String company);

    CompanyEntity findById(long id);
}
