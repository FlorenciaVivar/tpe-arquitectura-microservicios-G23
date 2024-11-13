package tpe.microserviciotrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tpe.microserviciotrip.dto.ReportTripDTO;
import tpe.microserviciotrip.entity.TripEntity;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<TripEntity,Long> {

    @Query("SELECT SUM(t.amount) FROM TripEntity t WHERE YEAR(t.endTime) = :year AND MONTH(t.endTime) BETWEEN :month1 AND :month2")
    Integer calculateTotalInvoiced( Integer year, Integer month1, Integer month2);

    @Query("SELECT new tpe.microserviciotrip.dto.ReportTripDTO(t.scooterId, SUM(t.distanceTraveled)) " +
            "FROM TripEntity t GROUP BY t.scooterId")
    List<ReportTripDTO> getTotalKilometersGroupedByScooterId();

    @Query("SELECT t.scooterId, COUNT(t) FROM TripEntity t WHERE t.year = :year GROUP BY t.scooterId HAVING COUNT(t) > :minTrips")
    List<Object[]> findScootersWithHighTripCountInYear(@Param("year") int year, @Param("minTrips") long minTrips); //devuelvo solo id de scooters
}