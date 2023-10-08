package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.SparePartsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SparePartsRepository extends CrudRepository<SparePartsEntity, Long> {

    Optional<SparePartsEntity> findByPartNumber(String SpareParts);

    void deleteByPartNumber(String partNumber);


}
