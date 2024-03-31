package Szakdolgozat.service.mapper;

import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.entities.UserEntity;
import Szakdolgozat.web.model.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserMapper {

    public UserEntity map(CompanyEntity companyEntity, CreateUserRequest createUserRequest){
        return UserEntity.builder()
                .name(createUserRequest.getName())
                .telNum(createUserRequest.getTelNum())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .title(createUserRequest.getTitle())
                .companyEntity(companyEntity)
                .build();
    }
}
