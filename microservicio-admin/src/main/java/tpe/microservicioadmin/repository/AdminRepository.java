package tpe.microservicioadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tpe.microservicioadmin.entity.AdminEntity;

import java.time.LocalDate;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

//    @Modifying
//    @Query("UPDATE admin_entity a SET a.active = false WHERE u.id = :id")
//    void inactive(@Param("id") Long id);

    // hay que cambiar los precios segun id?
    @Modifying
    @Query("UPDATE AdminEntity a SET a.normalPrice = :normalPrice, a.extraPrice = :extraPrice WHERE current_date  >= :date AND a.id = :id")
    void updatePricesInDate(@Param("id") Long id,@Param("normalPrice") Integer normalPrice, @Param("extraPrice") Integer extraPrice, @Param("date") LocalDate date);
}
