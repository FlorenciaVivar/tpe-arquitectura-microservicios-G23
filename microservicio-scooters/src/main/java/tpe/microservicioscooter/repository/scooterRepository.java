package tpe.microservicioscooter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpe.microservicioscooter.entities.scooterEntity;

import java.util.List;

@Repository
public interface scooterRepository extends JpaRepository<scooterEntity,Long> {

    List<scooterEntity> findByUserId(Long userId);
}
