package tpe.microserviciostation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpe.microserviciostation.entities.StationEntity;
import tpe.microserviciostation.repository.StationRepository;

import java.util.List;

@Service
public class StationService {
    @Autowired
    StationRepository stationRepository;

    // Agregar una nueva parada TAMBIEN LO DEBE HACE EL ADMIN
    public StationEntity addStation(StationEntity station) {
        return stationRepository.save(station);
    }

    public StationEntity findById(Long id){
        return stationRepository.findById(id).orElse(null);
    }

    public List<StationEntity> getAll(){
        return stationRepository.findAll();
    }



}
