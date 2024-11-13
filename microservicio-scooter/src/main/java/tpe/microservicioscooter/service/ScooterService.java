package tpe.microservicioscooter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpe.microservicioscooter.dto.ScooterKilometerDTO;
import tpe.microservicioscooter.dto.ScooterQuantityDTO;
import tpe.microservicioscooter.dto.TripDTO;
import tpe.microservicioscooter.entities.ScooterEntity;
import tpe.microservicioscooter.feignClients.TripFeignClient;
import tpe.microservicioscooter.repository.ScooterRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScooterService {
    @Autowired
    ScooterRepository scooterRepository;

    @Autowired
    TripFeignClient tripFeignClient;

    public List<ScooterEntity> getAll(){
        return scooterRepository.findAll();
    }

    public ScooterEntity save(ScooterEntity scooter){return scooterRepository.save(scooter);}

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

    public List<ScooterKilometerDTO> calculateTotalKilometersForScooter() {
        List<ScooterEntity> scooters = scooterRepository.findAll();
        List<ScooterKilometerDTO> scooterKilometerDTOList = new ArrayList<>();

        for (ScooterEntity scooter : scooters) {
            double totalKilometers = 0.0;
            try {
                List<TripDTO> trips = tripFeignClient.getTripsByScooterId(scooter.getId());

                for (TripDTO trip : trips) {
                    totalKilometers += trip.getDistanceTraveled();
                }
            } catch (Exception e) {
                System.err.println("Error al obtener los viajes para el scooter con ID " + scooter.getId() + ": " + e.getMessage());
            }
            scooterKilometerDTOList.add(new ScooterKilometerDTO(scooter.getId().toString(), totalKilometers));
        }

        return scooterKilometerDTOList;
    }

    public ScooterQuantityDTO calculateQuantityScooter() {
        Integer scootersAvailable = scooterRepository.countScootersAvailable();
        Integer scootersInMaintenance = scooterRepository.countScootersInMaintenance();
        return new ScooterQuantityDTO(scootersAvailable, scootersInMaintenance);
    }

    public List<ScooterEntity> findAllById(List<Long> scooterIds) {
        return scooterRepository.findAllById(scooterIds);
    }

}
