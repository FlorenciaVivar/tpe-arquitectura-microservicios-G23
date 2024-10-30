package tpe.microservicioscooter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpe.microservicioscooter.entities.scooterEntity;
import tpe.microservicioscooter.repository.scooterRepository;

import java.util.List;
@Service
public class scooterService {

    @Autowired
    scooterRepository scooterRepository;

    public List<scooterEntity> getAll(){
        return scooterRepository.findAll();
    }

    public scooterEntity save(scooterEntity scooter){
        scooterEntity scooterNew;
        scooterNew = scooterRepository.save(scooter);
        return scooterNew;
    }

    public void delete(scooterEntity scooter){
        scooterRepository.delete(scooter);
    }
    public scooterEntity findById(Long id){
        return scooterRepository.findById(id).orElse(null);
    }

}