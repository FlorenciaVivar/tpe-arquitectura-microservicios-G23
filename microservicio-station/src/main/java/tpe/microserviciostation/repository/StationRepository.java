package tpe.microserviciostation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tpe.microserviciostation.entities.StationEntity;

public interface StationRepository extends JpaRepository<StationEntity,Long> {


}
