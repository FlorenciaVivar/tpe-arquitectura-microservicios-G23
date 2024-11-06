package tpe.microserviciouser1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microserviciouser1.entities.UserEntity;
import tpe.microserviciouser1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> user = userService.getAll();
        if (user.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id){
        UserEntity user = userService.findById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    //Agregar monopatín
    @PostMapping("")
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user){
        UserEntity savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        UserEntity user = userService.findById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        userService.delete(user);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/scooter/{userId}")
//    public ResponseEntity<List<scooterEntity>> getScooterUsers(@PathVariable("userId") Long userId){
//        userEntity user =userService.getUserById(userId);
//        if(user == null){
//            return ResponseEntity.notFound().build();
//        }
//        List<scooterEntity> scooterUsers = userService.getScooter(userId);
//        return ResponseEntity.ok(scooterUsers);
//    }


}
