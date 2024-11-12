package tpe.microserviciouser1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import tpe.microserviciouser1.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("UPDATE UserEntity u SET u.active = false WHERE u.id = :id")
    void updateInactive(@Param("id") Long id);

}
