package Szakdolgozat.repository;

import Szakdolgozat.entities.ToolEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToolRepository extends CrudRepository<ToolEntity, Long> {
       List<ToolEntity> findByStatusContainingIgnoreCase(String status);
       List<ToolEntity> findByNameContainingIgnoreCase(String name);
       List<ToolEntity> findByTypeNumberContainingIgnoreCase(String typeNumber);
       List<ToolEntity> findBySerialNumberContainingIgnoreCase(String serialNumber);
       List<ToolEntity> findByItemNumberContainingIgnoreCase(String itemNumber);

       List<ToolEntity> findByIdentifierContainingIgnoreCase(String identifier);

       @Query("select count(*) from ToolEntity where YEAR(dateOfReceiving) = :year and MONTH(dateOfReceiving) = :month")
       Long getItemCountInMonth(@Param("year") int year, @Param("month") int month);


       ToolEntity findByIdEquals(long id);


}
