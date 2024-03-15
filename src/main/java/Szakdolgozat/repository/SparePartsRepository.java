package Szakdolgozat.repository;

import Szakdolgozat.entities.SparePartsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SparePartsRepository extends CrudRepository<SparePartsEntity, Long> {

    Optional<SparePartsEntity> findByPartNumberContainingIgnoreCase(String number);

    Iterable<SparePartsEntity> findByPartNameIsContainingIgnoreCase(String name);

    Iterable<SparePartsEntity> findByPartNumberContaining(String number);
    void deleteByPartNumber(String partNumber);


    Optional<SparePartsEntity> findByid(long id);

    Optional<SparePartsEntity> findByPartNumber(String number);
}
