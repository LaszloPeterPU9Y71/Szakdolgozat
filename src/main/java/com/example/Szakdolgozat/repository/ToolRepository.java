package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.ToolEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToolRepository extends CrudRepository<ToolEntity, Long> {
       List<ToolEntity> findByStatus(String status);
       List<ToolEntity> findByName(String name);
       List<ToolEntity> findByTypeNumber(String typeNumber);
       List<ToolEntity> findBySerialNumber(String serialNumber);
       List<ToolEntity> findByItemNumber(String itemNumber);
}
