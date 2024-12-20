package tpe.microserviciouser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import tpe.microservicioadmin.model.Scooter;
import tpe.microserviciouser.entity.UserEntity;
import tpe.microserviciouser.repository.UserRepository;

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

    public void updateInactive(Long id){ userRepository.updateInactive(id); }


}
