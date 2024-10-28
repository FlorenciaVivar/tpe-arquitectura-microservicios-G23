package tpe.microservicioadmin;
import com.example.microservicioadmin.entity.AdminEntity;
import com.example.microservicioadmin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<AdminEntity> getAll(){
        return adminRepository.findAll();
    }

    public AdminEntity save(AdminEntity admin){
        AdminEntity adm;
        adm = adminRepository.save(admin);
        return adm;
    }
    public void delete(AdminEntity adm){

        AdminRepository.delete(adm);
    }

    public AdminEntity findById(Long id){
        return adminRepository.findById(id).orElse(null);
    }

    public AdminEntity update(AdminEntity adm){
        return adminRepository.save(adm);
    }

    public List<AdminEntity> byUserId(Long userid){
        return adminRepository.findByUserId(userid);
    }
}