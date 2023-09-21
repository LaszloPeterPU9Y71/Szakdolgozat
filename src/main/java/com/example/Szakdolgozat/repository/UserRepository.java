package com.example.Szakdolgozat.repository;

import com.example.Szakdolgozat.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

    Iterable<UserEntity> findByName(String name);

    Optional<UserEntity> findByEmail(String email);


}

