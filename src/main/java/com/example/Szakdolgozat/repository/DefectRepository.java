package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.DefectEntity;
import org.springframework.data.repository.CrudRepository;

public interface DefectRepository extends CrudRepository<DefectEntity, Long> {

    Iterable<DefectEntity> findByName(String name);
}
