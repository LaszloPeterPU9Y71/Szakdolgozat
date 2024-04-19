package Szakdolgozat.service;

import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataAlreadyExistsException;
import Szakdolgozat.ExceptionHandler.customExceptionHandler.DataNotFoundException;
import Szakdolgozat.entities.CompanyEntity;
import Szakdolgozat.entities.OwnerCompanyEmployeeEntity;
import Szakdolgozat.entities.OwnerCompanyEntity;
import Szakdolgozat.entities.UserEntity;
import Szakdolgozat.repository.CompanyRepository;
import Szakdolgozat.repository.UserRepository;
import Szakdolgozat.service.mapper.UserMapper;
import Szakdolgozat.service.mapper.entityToDto.UserMapStructDto;
import Szakdolgozat.web.dto.UserDto;
import Szakdolgozat.web.model.CreateUserRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CompanyRepository companyRepository;
    @Qualifier("userMapStructDtoImplementation")
    private final UserMapStructDto userMapStructDto;



    public List<UserDto> findAllUsers() throws DataNotFoundException{
        Iterable<UserEntity> userEntities =  userRepository.findAll();
        if(userMapStructDto.fromEntitytoDtoList(userEntities).isEmpty()){
            throw new DataNotFoundException("Az adatbázisban még nem szerepelnek felhasználók");
        }
        return userMapStructDto.fromEntitytoDtoList(userEntities);


    }

    public UserDto addUser(CreateUserRequest createUserRequest) throws DataAlreadyExistsException {
        String email = createUserRequest.getEmail();
        CompanyEntity companyEntity = companyRepository.findByIdEquals(createUserRequest.getCompanyId());
        Optional<UserEntity> maybeEmail = userRepository.findByEmail(email);

        if (maybeEmail.isPresent()) {
            throw new DataAlreadyExistsException(String.format("Ez az e-mail cím már használatban van: '%s'", email));
        }

        UserEntity user = userMapper.map(companyEntity, createUserRequest);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus(true);
        UserEntity userEntity = userRepository.save(user);
        return userMapStructDto.fromEntityToDto(userEntity);
    }


    public void softDelete(long id) throws DataNotFoundException {
        if (userRepository.findById(id).isEmpty()){
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("Nem található felhasználó ezzel az azonosítóval: %s", id));
        }
            userRepository.deleteById(id);

    }

    public void updateUserPersonalData(long id, CreateUserRequest createUserRequest) throws RuntimeException {
        Optional<UserEntity> maybeUserEntity = userRepository.findById(id);
        Optional<UserEntity> existingEmail = userRepository.findByEmail(createUserRequest.getEmail());

        if (maybeUserEntity.isEmpty()) {
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("Nem található felhasználó ezzel az azonosítóval: %s", id));
        } else if (existingEmail.isPresent()) {
            throw new DataAlreadyExistsException(HttpStatus.NOT_FOUND, String.format("Ez az e-mail cím már használatban van: %s !", existingEmail.get().getEmail()));
        }
        userRepository.save(updateUserPersonalData(maybeUserEntity.get(), createUserRequest));
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

    public void updateUserPassword(long id, CreateUserRequest createUserRequest) throws DataNotFoundException {
        Optional<UserEntity> maybeUserEntity = userRepository.findById(id);

        if (maybeUserEntity.isEmpty()) {
            throw new DataNotFoundException(HttpStatus.NOT_FOUND, String.format("Nem található felhasználó ezzel az azonosítóval: %s", id));
        }
        userRepository.save(updateUserPassword(maybeUserEntity.get(), createUserRequest));
    }

    private UserEntity updateUserPassword(UserEntity current, CreateUserRequest createUserRequest) {
        current.setPassword(new BCryptPasswordEncoder().encode(createUserRequest.getPassword()));
        return current;
    }

    public void companyAssignUser(long companyId, long userId) throws DataNotFoundException {
        UserEntity userEntity = userRepository.findByIdEquals(userId);
        System.out.println(userEntity);
        if(userEntity == null){
            throw new DataNotFoundException(String.format("Ezzel az azonosítóval nem található alkalmazott: %s", userId));
        }
        CompanyEntity companyEntity = companyRepository.findById(companyId);
        System.out.println(companyEntity);
        if(companyEntity == null){
            throw new DataNotFoundException(String.format("Ezzel az azonosítóval nem található cég: %s", companyId ));
        }
        userEntity.setCompanyEntity(companyEntity);

    }

    public void companyUnassignUser(long userId) throws DataNotFoundException {
        UserEntity userEntity = userRepository.findByIdEquals(userId);
        if(userEntity == null){
            throw new DataNotFoundException(String.format("Ezzel az azonosítóval nem található alkalmazott: %s", userId));
        }else if(userEntity.getCompanyEntity() == null){
            throw new DataNotFoundException("Ehhez a személyhez nincs hozzárendelve cég");
        }else userEntity.setCompanyEntity(null);
    }
}
