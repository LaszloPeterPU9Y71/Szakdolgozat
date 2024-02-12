package Szakdolgozat.repository;

import Szakdolgozat.entities.ToolEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToolRepository extends CrudRepository<ToolEntity, Long> {
       List<ToolEntity> findByStatusContainingIgnoreCase(String status);
       List<ToolEntity> findByNameContainingIgnoreCase(String name);
       List<ToolEntity> findByTypeNumberContainingIgnoreCase(String typeNumber);
       List<ToolEntity> findBySerialNumberContainingIgnoreCase(String serialNumber);
       List<ToolEntity> findByItemNumberContainingIgnoreCase(String itemNumber);

}
