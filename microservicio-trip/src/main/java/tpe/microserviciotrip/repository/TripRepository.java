package tpe.microserviciotrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpe.microserviciotrip.entity.TripEntity;


@Repository
public interface TripRepository extends JpaRepository<TripEntity,Long> {

}