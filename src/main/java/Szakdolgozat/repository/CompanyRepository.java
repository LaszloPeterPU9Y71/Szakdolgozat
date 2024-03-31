package Szakdolgozat.repository;

import Szakdolgozat.entities.CompanyEntity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {


    Optional<CompanyEntity> findByTaxNumber(String taxNumber);

    Iterable<CompanyEntity> findByNameContainingIgnoreCase(String company);

    CompanyEntity findById(long id);

    CompanyEntity findByIdEquals(Long id);




}
