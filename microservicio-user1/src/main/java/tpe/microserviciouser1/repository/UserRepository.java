package tpe.microserviciouser1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpe.microserviciouser1.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
