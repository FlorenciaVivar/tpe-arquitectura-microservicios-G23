package tpe.microserviciouser1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpe.microserviciouser1.entities.UserEntity;
import tpe.microserviciouser1.repository.UserRepository;

import java.util.List;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }

    public UserEntity save(UserEntity user){ return userRepository.save(user);}

    public void delete(UserEntity user){
        userRepository.delete(user);
    }

    public UserEntity findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

}
