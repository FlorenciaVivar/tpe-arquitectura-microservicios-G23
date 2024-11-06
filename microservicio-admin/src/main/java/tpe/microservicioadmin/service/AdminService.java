package tpe.microservicioadmin.service;

import tpe.microservicioadmin.entity.AdminEntity;
import tpe.microservicioadmin.feignClients.ScooterFeignClient;
import tpe.microservicioadmin.model.Scooter;
import tpe.microservicioadmin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpe.microservicioadmin.feignClients.UserFeignClient;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    ScooterFeignClient scooterFeignClient;

    public List<AdminEntity> getAll() {
        return adminRepository.findAll();
    }

    public AdminEntity save(AdminEntity admin) {return  adminRepository.save(admin);}

    public void delete(AdminEntity adm) { adminRepository.delete(adm);}

    public AdminEntity findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public AdminEntity update(AdminEntity adm) {
        return adminRepository.save(adm);
    }

    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }


}