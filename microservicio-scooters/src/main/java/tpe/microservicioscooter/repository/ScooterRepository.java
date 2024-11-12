package tpe.microservicioscooter.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tpe.microservicioscooter.dto.ScooterQuantityDTO;
import tpe.microservicioscooter.entities.ScooterEntity;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<ScooterEntity,Long> {

    List<ScooterEntity> findByStatus(String status);

    //Encontrar todos los monopatines en X parada
    List<ScooterEntity> findByScooterStop(String scooterStop);

    //Reporte de uso de monopatines por kilómetros
    @Query("SELECT s FROM ScooterEntity s")
    List<ScooterEntity> findAllWithKilometers();

    @Query("SELECT COUNT(s) FROM ScooterEntity s WHERE s.available = true")
    int countScootersAvailable();

    @Query("SELECT COUNT(s) FROM ScooterEntity s WHERE s.available = false")
    int countScootersInMaintenance();

}
