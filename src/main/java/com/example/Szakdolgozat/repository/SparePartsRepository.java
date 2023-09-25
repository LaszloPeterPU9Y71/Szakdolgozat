package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.entities.SparePartsEntity;
import org.springframework.data.repository.CrudRepository;

public interface SparePartsRepository extends CrudRepository<SparePartsEntity, Long> {

    Iterable<SparePartsEntity> findByitemNumber(String SpareParts);

}
