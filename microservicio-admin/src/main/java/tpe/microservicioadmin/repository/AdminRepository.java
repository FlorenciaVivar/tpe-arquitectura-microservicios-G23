package tpe.microservicioadmin.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tpe.microservicioadmin.entity.AdminEntity;

import java.time.LocalDate;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AdminEntity a SET a.normalPrice = :normalPrice, a.extraPrice = :extraPrice WHERE a.id = :id")
    void updatePricesInDate(@Param("id") Long id,@Param("normalPrice") Integer normalPrice, @Param("extraPrice") Integer extraPrice);
}
