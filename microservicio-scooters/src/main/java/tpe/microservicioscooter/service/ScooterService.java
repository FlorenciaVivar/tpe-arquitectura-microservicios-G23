package tpe.microservicioscooter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpe.microservicioscooter.entities.ScooterEntity;
import tpe.microservicioscooter.repository.ScooterRepository;

import java.util.List;

@Service
public class ScooterService {
    @Autowired
    ScooterRepository scooterRepository;

    public List<ScooterEntity> getAll(){
        return scooterRepository.findAll();
    }
    public ScooterEntity save(ScooterEntity scooter){
        ScooterEntity scooterNew;
        scooterNew = scooterRepository.save(scooter);
        return scooterNew;
    }

    public ScooterEntity findById(Long id){
        return scooterRepository.findById(id).orElse(null);
    }
    //obtener todos los monopatines disponibles
    public List<ScooterEntity> getAvailableScooters() {
        return scooterRepository.findByStatus("available");
    }

    public void delete(Long id) {
        scooterRepository.deleteById(id);
    }


    //Registrar monopatín en mantenimiento
    public ScooterEntity setScooterInMaintenance(Long id) {
        ScooterEntity scooter = scooterRepository.findById(id).orElse(null);
        if (scooter != null) {
            scooter.setStatus("maintenance");
            scooter.setAvailable(false); // para marcarlo como no disponible
            return scooterRepository.save(scooter);
        }
        return null;
    }
    //registrar fin de mantenimiento
    public ScooterEntity finishMaintenance(Long id) {
        ScooterEntity scooter = scooterRepository.findById(id).orElse(null);
        if (scooter != null) {
            scooter.setStatus("available");
            scooter.setAvailable(true); // para marcarlo como disponible
            return scooterRepository.save(scooter);
        }
        return null;
    }
    //ubicar todos los monopatines en parada
    public List<ScooterEntity> getScootersByStop(String scooterStop) {
        return scooterRepository.findByScooterStop(scooterStop);
    }

    public ScooterEntity updateScooter(Long id, ScooterEntity scooterDetails) {
        ScooterEntity scooter = scooterRepository.findById(id).orElse(null);
        if (scooter != null) {
            scooter.setStatus(scooterDetails.getStatus());
            scooter.setAvailable(scooterDetails.isAvailable());
            scooter.setGps(scooterDetails.getGps());
            scooter.setTotalKilometers(scooterDetails.getTotalKilometers());
            scooter.setTimeInUse(scooterDetails.getTimeInUse());
            return scooterRepository.save(scooter);
        }
        return null;
    }

//    public List<scooterKilometerDTO> getScooterKilometers() {
//
//        return scooterKilometers;
//    }
}
