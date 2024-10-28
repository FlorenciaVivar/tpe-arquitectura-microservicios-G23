package tpe.microserviciotrip;

import com.example.microserviciobike.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long>{

    List<Trip> findBytripId(Long Trip);
}