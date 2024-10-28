package tpe.microservicioadmin

import com.example.microserviciobike.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface adminRepository extends JpaRepository<AdminEntity,Long>{

    List<AdminEntity> findByUserId(Long userId);
}
