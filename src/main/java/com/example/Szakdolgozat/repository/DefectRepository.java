package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.DefectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DefectRepository extends CrudRepository<DefectEntity, Long> {

    Optional<DefectEntity> findByName(String name);
}
