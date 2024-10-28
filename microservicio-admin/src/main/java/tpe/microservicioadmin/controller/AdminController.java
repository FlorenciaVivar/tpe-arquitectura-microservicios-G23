
package tpe.microservicioadmin

import com.example.microserviciobike.entity.AdminEntity;
import com.example.microservicioadmin.service.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class adminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/")
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
        AdminEntity e.adm = adminServiceService.save(adm);
        return ResponseEntity.ok(e.adm);
    }

    public void Set



}