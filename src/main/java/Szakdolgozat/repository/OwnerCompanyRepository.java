package Szakdolgozat.repository;

import Szakdolgozat.entities.OwnerCompanyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerCompanyRepository extends CrudRepository<OwnerCompanyEntity, Long> {

    List<OwnerCompanyEntity> findByCompanyNameContainingIgnoreCase(String name);

    OwnerCompanyEntity findByCompanyNameContaining(String name);

    Optional<OwnerCompanyEntity> findByTaxNumber(String taxNumber);

    OwnerCompanyEntity findById(long id);


    boolean existsByTaxNumber(String taxNumber);

    void deleteByTaxNumber(String taxNumber);
}
