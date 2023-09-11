package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.domain.Ceg;
import org.springframework.data.repository.CrudRepository;

public interface CegRepository extends CrudRepository<Ceg, Long> {

    Iterable<Ceg> findByCompany(String company);
}
