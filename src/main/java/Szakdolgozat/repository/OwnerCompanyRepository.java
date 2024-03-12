package Szakdolgozat.repository;

import Szakdolgozat.entities.OwnerCompanyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerCompanyRepository extends CrudRepository<OwnerCompanyEntity, Long> {

    List<OwnerCompanyEntity> findByCompanyNameContainingIgnoreCase(String name);

    OwnerCompanyEntity findByTaxNumberContaining(String taxNumber);

    Optional<OwnerCompanyEntity> findByTaxNumber(String taxNumber);


    boolean existsByTaxNumber(String taxNumber);

    void deleteByTaxNumber(String taxNumber);
}
