package tpe.microserviciouser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpe.microserviciouser.entities.userEntity;

@Repository
public interface userRepository extends JpaRepository<userEntity,Long> {
}
