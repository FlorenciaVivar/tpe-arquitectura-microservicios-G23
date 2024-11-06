package tpe.microserviciotrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpe.microserviciotrip.entity.TripEntity;
import tpe.microserviciotrip.repository.TripRepository;

import java.util.List;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public List<TripEntity> getAll(){
        return tripRepository.findAll();
    }

    public TripEntity save(TripEntity T){ return tripRepository.save(T); }

    public void delete(TripEntity trip){ tripRepository.delete(trip); }

    public TripEntity findById(Long id){
        return tripRepository.findById(id).orElse(null);
    }

    public TripEntity update(TripEntity t){
        return tripRepository.save(t);
    }

}