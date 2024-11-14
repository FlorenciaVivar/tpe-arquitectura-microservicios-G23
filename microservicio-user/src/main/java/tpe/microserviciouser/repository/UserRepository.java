package tpe.microserviciouser.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tpe.microserviciouser.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.active = false WHERE u.id = :id")
    void updateInactive(Long id);

}
