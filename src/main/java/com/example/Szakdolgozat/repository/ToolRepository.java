package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.ToolEntity;
import org.springframework.data.repository.CrudRepository;

public interface ToolRepository extends CrudRepository<ToolEntity, Long> {

    Iterable<ToolEntity> findByName(String name);
}
