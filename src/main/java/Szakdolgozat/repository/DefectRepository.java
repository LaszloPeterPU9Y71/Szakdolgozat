package Szakdolgozat.repository;

import Szakdolgozat.entities.DefectEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DefectRepository extends CrudRepository<DefectEntity, Long> {

    List<DefectEntity> findByNameContainingIgnoreCase(String name);

    Optional<DefectEntity> findByName(String name);

    DefectEntity findById(long id);

}
