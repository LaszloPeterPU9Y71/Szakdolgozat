package Szakdolgozat.service;

import Szakdolgozat.entities.UserEntity;
import Szakdolgozat.repository.CompanyRepository;
import Szakdolgozat.repository.UserRepository;
import Szakdolgozat.service.mapper.UserMapStructDto;
import Szakdolgozat.service.mapper.UserMapper;
import Szakdolgozat.web.dto.UserDto;
import Szakdolgozat.web.model.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CompanyRepository companyRepository;
    private final UserMapStructDto userMapStructDto;





    public UserDto addUser(CreateUserRequest createUserRequest) throws Exception {
        String email = createUserRequest.getEmail();
        Optional<UserEntity> maybeEmail = userRepository.findByEmail(email);

        if (maybeEmail.isPresent()) {

            throw new Exception(String.format("Ez az e-mail cím már használatban van: '%s'", email));
        }

        UserEntity user = userMapper.map(createUserRequest);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus(true);
        user.setCompanyEntity(companyRepository.findById(1));
        UserEntity userEntity = userRepository.save(user);
        return userMapStructDto.fromEntityToDto(userEntity);
    }


    public void softDelete(long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", id));
        } else {
            userRepository.deleteById(id);
        }
    }

    public void updateUserPersonalData(long id, CreateUserRequest createUserRequest) {
        Optional<UserEntity> maybeUserEntity = userRepository.findById(id);
        Optional<UserEntity> existingEmail = userRepository.findByEmail(createUserRequest.getEmail());

        if (maybeUserEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", id));
        } else if (existingEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User e-mail address already exists: %s", existingEmail.get().getEmail()));
        }
        userRepository.save(updateUserPersonalData(maybeUserEntity.get(), createUserRequest));
    }

    public Optional<UserEntity> updateUserPassword(long id, CreateUserRequest createUserRequest) {
        Optional<UserEntity> maybeUserEntity = userRepository.findById(id);

        if (maybeUserEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", id));
        }
        return Optional.of(userRepository.save(updateUserPassword(maybeUserEntity.get(), createUserRequest)));
    }


    private UserEntity updateUserPersonalData(UserEntity current, CreateUserRequest createUserRequest) {
        current.setName(createUserRequest.getName());
        current.setEmail(createUserRequest.getEmail());
        current.setTelNum(createUserRequest.getTelNum());
        current.setTitle(createUserRequest.getTitle());
        current.setStatus(createUserRequest.isStatus());
        current.setPassword(new BCryptPasswordEncoder().encode(createUserRequest.getPassword()));
        return current;
    }

    private UserEntity updateUserPassword(UserEntity current, CreateUserRequest createUserRequest) {
        current.setPassword(new BCryptPasswordEncoder().encode(createUserRequest.getPassword()));
        return current;
    }

}
