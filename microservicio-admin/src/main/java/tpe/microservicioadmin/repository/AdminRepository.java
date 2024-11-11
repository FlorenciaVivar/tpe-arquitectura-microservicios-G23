package tpe.microservicioadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tpe.microservicioadmin.entity.AdminEntity;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    @Modifying
    @Query("UPDATE admin_entity a SET a.active = false WHERE a.id = :id")
    void inactive(@Param("id") Long id);

    // hay que cambiar los precios segun id?
    @Modifying
    @Query("UPDATE admin_entity a SET a.tarifa_extra = :extraPrice AND a.tarifa_normal = :normalPrice")
    void updatePrices(@Param("normalPrice") Integer normalPrice, @Param("normalPrice") Integer extraPrice);

}
