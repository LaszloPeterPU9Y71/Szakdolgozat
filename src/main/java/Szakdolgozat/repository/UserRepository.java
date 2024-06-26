package Szakdolgozat.repository;

import Szakdolgozat.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

    Optional<UserEntity> findByEmail(String email);

    UserEntity findByEmailEquals(String email);
    UserEntity findByIdEquals(long id);







}

