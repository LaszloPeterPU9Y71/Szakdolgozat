package Szakdolgozat.web.controller;

import Szakdolgozat.service.UserService;
import Szakdolgozat.web.dto.ToolDto;
import Szakdolgozat.web.dto.UserDto;
import Szakdolgozat.web.model.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor


public class UserController {


    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        UserDto userDto = userService.addUser(createUserRequest);
        return ResponseEntity.ok(userDto);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAllUsers(){
        List<UserDto> userDto = userService.findAllUsers();
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/find-user-by-email/{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable (value = "email") String email){
        UserDto userDto = userService.findUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable("id") long id) {
        userService.softDelete(id);
        return ResponseEntity.ok("Az " + id + " azonosítójú felhasználó törlésre került");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUserData(@PathVariable("id") long id,
                                                 @RequestBody CreateUserRequest createUserRequest) {
            userService.updateUserPersonalData(id, createUserRequest);
        return ResponseEntity.ok("A felhasználó adatai frissültek!");
    }

    @PutMapping("/pw/{id}")
    public ResponseEntity<String> updateUserPassword(@PathVariable("id") long id,
                                                     @RequestBody CreateUserRequest createUserRequest) {
        userService.updateUserPassword(id, createUserRequest);
        return ResponseEntity.ok( "A felhasználó jelszava frissült");
    }


    @PutMapping("/company-assign-user/{companyId}/{userId}")
    public ResponseEntity<String> companyAssignUser(@PathVariable (value = "companyId") long companyId, @PathVariable (value = "userId") long userId) {
        userService.companyAssignUser(companyId, userId);
        return ResponseEntity.ok("Az alkalmazotthoz hozzárendelésre került a cége.");


    }
    @PutMapping("/company-unassign-user/{userId}")
    public ResponseEntity<String> companyUnassignUser(@PathVariable (value = "userId") long userId) {
        userService.companyUnassignUser(userId);
        return ResponseEntity.ok("A cég és alkalmazott összerendelés megszűnt.");


    }
}





