package tpe.microserviciotrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpe.microserviciotrip.dto.ReportTripDTO;
import tpe.microserviciotrip.dto.ScooterMinTripsDTO;
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


    public TripEntity findById(Long id){
        return tripRepository.findById(id).orElse(null);
    }

    public TripEntity update(TripEntity t){
        return tripRepository.save(t);
    }

    public Integer calculateTotalInvoiced(Integer year, Integer month1, Integer month2) {return tripRepository.calculateTotalInvoiced(year,month1,month2);    }

    public void deleteById(Long id) {
        tripRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return tripRepository.existsById(id);
    }

    public List<ReportTripDTO> getReportKmByScooters() { return tripRepository.getTotalKilometersGroupedByScooterId(); }

    public List<ScooterMinTripsDTO> findScootersWithMinTripsInYear(Integer year, Integer minTrips) {return tripRepository.findScootersWithHighTripCountInYear(year, minTrips) ;  }
}
