package tpe.microserviciotrip;
import com.example.microservicioadmin.entity.Trip;
import com.example.microservicioadmin.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public List<Trip> getAll(){
        return tripRepository.findAll();
    }

    public Trip save(Trip T){
        Trip tr;
        tr = tripRepository.save(T);
        return tr;
    }
    public void delete(Trip trip){

       tripRepository.delete(trip);
    }

    public Trip findById(Long id){
        return tripRepository.findById(id).orElse(null);
    }

    public Trip update(Trip t){
        return tripRepository.save(t);
    }

    public List<Trip> findByTripIdId(Long trip){
        return tripRepository.findByTripId(trip);
    }
}