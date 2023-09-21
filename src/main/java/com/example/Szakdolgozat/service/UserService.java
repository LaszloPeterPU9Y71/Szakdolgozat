package com.example.Szakdolgozat.service;

import com.example.Szakdolgozat.domain.UserEntity;
import com.example.Szakdolgozat.repository.UserRepository;
import com.example.Szakdolgozat.service.mapper.UserMapper;
import com.example.Szakdolgozat.web.model.CreateUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor

public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserEntity addUser(CreateUserRequest createUserRequest) throws Exception {
        String email = createUserRequest.getEmail();
        Optional<UserEntity> maybeEmail = userRepository.findByEmail(email);
        if (maybeEmail.isPresent()) {

            throw new Exception(String.format("User e-mail address already exists: '%s'", email));
        }

        UserEntity user = userMapper.map(createUserRequest);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }


    public void softDelete(long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", id));
        } else {
            userRepository.deleteById(id);
        }
    }

   /* public Optional<UserEntity> updateUserPersonalData(long id, CreateUserRequest createUserRequest) {
        Optional<UserEntity> maybeUserEntity = userRepository.findById(id);
        Optional<UserEntity> existingEmail = userRepository.findByEmail(createUserRequest.getEmail());

        if (maybeUserEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", id));
        } else if (existingEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User e-mail address already exists: %s", existingEmail.get().getEmail()));
        }
        return Optional.of(userRepository.save(updateUserPersonalData(maybeUserEntity.get(), createUserRequest)));
        //Optional.of(userRepository.save(updateUserPersonalData(maybeUserEntity.get(), createUserRequest)));
    }

    public Optional<UserEntity> updateUserPassword(long id, CreateUserRequest createUserRequest) {
        Optional<UserEntity> maybeUserEntity = userRepository.findById(id);

        if (maybeUserEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", id));
        }
        return Optional.of(userRepository.save(updateUserPassword(maybeUserEntity.get(), createUserRequest)));
    }

    public List<UserEntity> findAllUser(PagingSortingFilteringRequest pagingSortingFilteringRequest) {
        List<UserEntity> listedUsers = new ArrayList<>();

        final Pageable pageable = PageRequest.of(pagingSortingFilteringRequest.getPage(),
                pagingSortingFilteringRequest.getSize(),
                Sort.by(Sort.Direction.valueOf(pagingSortingFilteringRequest.getSorting().toString()),
                        pagingSortingFilteringRequest.getSort().toString()));

        UserEntity entityAsProbe = UserEntity.builder().name(pagingSortingFilteringRequest.getSearch()).build();

        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<UserEntity> example = Example.of(entityAsProbe, customExampleMatcher);

        final Page<UserEntity> page = userRepository.findAll(example, pageable);
        for (UserEntity userEntity : page) {
            listedUsers.add(userEntity);
        }
        return listedUsers;
    }*/


}
