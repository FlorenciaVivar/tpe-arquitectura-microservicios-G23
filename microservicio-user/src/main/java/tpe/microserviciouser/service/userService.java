package tpe.microserviciouser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tpe.microserviciouser.entities.userEntity;
import tpe.microserviciouser.repository.userRepository;

import java.util.List;

@Service
public class userService {

    @Autowired
    userRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<userEntity> getAll(){
        return userRepository.findAll();
    }

    public userEntity save(userEntity user){
        userEntity userNew;
        userNew = userRepository.save(user);
        return userNew;
    }
    public void delete(userEntity user){

        userRepository.delete(user);
    }

    public userEntity getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public userEntity update(userEntity user){
        return userRepository.save(user);
    }

//    public List<scooterEntity> getScooter(Long userId) {
//        List<scooterEntity> scooters = restTemplate.getForObject("http://localhost:8002/scooter/byUser/" + userId, List.class);
//        return scooters;
//    }
}
