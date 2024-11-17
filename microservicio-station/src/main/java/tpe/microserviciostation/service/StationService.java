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

    public StationEntity addStation(StationEntity station) { return stationRepository.save(station); }

    public StationEntity findById(String id){
        return stationRepository.findById(id).orElse(null);
    }

    public List<StationEntity> getAll(){
        return stationRepository.findAll();
    }

    public void delete(StationEntity station){ stationRepository.delete(station);}


}
