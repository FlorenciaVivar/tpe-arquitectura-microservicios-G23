package tpe.microserviciouser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microserviciouser.entities.userEntity;
import tpe.microserviciouser.models.Scooter;
import tpe.microserviciouser.service.userService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {

    @Autowired
    userService userService;

    @GetMapping("/")
    public ResponseEntity<List<userEntity>> getAllUser(){
        List<userEntity> users = userService.getAllUser();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);  // Return OK status and list of users if they exist
    }

    @GetMapping("/{id}")
    public ResponseEntity<userEntity> getUserById(@PathVariable("id") Long id){
        userEntity user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);  // Return OK status and user if it exists
    }
    @PostMapping("")
    public ResponseEntity<userEntity> save(@RequestBody userEntity user){
        userEntity userNew = userService.save(user);
        return ResponseEntity.ok(userNew);  // Return OK status and new user if saved
    }

    @GetMapping("/scooter/{userId}")
    public ResponseEntity<List<Scooter>> getScooterUsers(@PathVariable("userId") Long userId){
        userEntity user =userService.getUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        List<Scooter> scooterUsers = userService.getScooter(userId);
        return ResponseEntity.ok(scooterUsers);
    }


}
