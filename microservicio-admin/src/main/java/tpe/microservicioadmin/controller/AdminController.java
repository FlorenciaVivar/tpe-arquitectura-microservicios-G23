package tpe.microservicioadmin.controller;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microservicioadmin.service.AdminService;
import tpe.microservicioadmin.entity.AdminEntity;
import tpe.microservicioadmin.feignClients.UserFeignClient;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserFeignClient userFeignClient;

    @GetMapping("")
    public ResponseEntity<List<AdminEntity>> getAllAdmin() {
        List<AdminEntity> adm = adminService.getAll();
        if (adm.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(adm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminEntity> getAdminById(@PathVariable("id") Long id) {
        AdminEntity adm = adminService.findById(id);
        if (adm == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adm);
    }

    @PostMapping("")
    public ResponseEntity<AdminEntity> save(@RequestBody AdminEntity adm) {
        AdminEntity savedAdm = adminService.save(adm);
        return ResponseEntity.ok(savedAdm);
    }
    //Eliminar un admin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    //Eliminar un usario desde admin
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userFeignClient.delete(id);
            return ResponseEntity.noContent().build();
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Registrar un monopatin en mantenimiento

}