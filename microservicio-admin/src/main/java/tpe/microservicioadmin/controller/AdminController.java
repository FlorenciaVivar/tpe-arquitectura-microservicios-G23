package tpe.microservicioadmin.controller;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microservicioadmin.feignClients.ScooterFeignClient;
import tpe.microservicioadmin.feignClients.StationFeignClient;
import tpe.microservicioadmin.model.Scooter;
import tpe.microservicioadmin.model.Station;
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
    @Autowired
    ScooterFeignClient scooterFeignClient;
    @Autowired
    StationFeignClient stationFeignClient;

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
    // Agregar monopatín
    @PostMapping("/scooter/addScooter")
    public ResponseEntity<Scooter> addScooter(@RequestBody Scooter scooter) {
        ResponseEntity<Scooter> respuesta = scooterFeignClient.save(scooter);
        return respuesta;
    }
    //eliminar un monopatin
    @DeleteMapping("/scooter/deleteScooter/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable Long id) {
        try {
            scooterFeignClient.delete(id);
            return ResponseEntity.noContent().build();
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Registrar un monopatin en mantenimiento
    @PutMapping("/scooter/maintenance/{id}")
    public ResponseEntity<Scooter> registerMaintenance(@PathVariable Long id) {
        try {
            ResponseEntity<Scooter> response = scooterFeignClient.registerMaintenance(id);
            return response;
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //registrar fin del mantenimietno del monopatin
    @PutMapping("/scooter/finishMaintenance/{id}")
    public ResponseEntity<Scooter> finishMaintenance(@PathVariable Long id) {
        try {
            ResponseEntity<Scooter> response = scooterFeignClient.finishMaintenance(id);
            return response;
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //eliminar un parada
    @DeleteMapping("/stations/deleteStation/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        try {
            stationFeignClient.delete(id);
            return ResponseEntity.noContent().build();
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Agregar parada
    @PostMapping("/stations/addStation")
    public ResponseEntity<Station> addStation(@RequestBody Station station) {
        ResponseEntity<Station> respuesta = stationFeignClient.save(station);
        return respuesta;
    }

    // 3.b) Cambiar estado de cuenta
    @PutMapping("/users/active/{id}")
    public ResponseEntity<Void> setActive(@PathVariable Long id){
        try {
            ResponseEntity<Void> response= userFeignClient.inactive(id);
            return response;
        }catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 3.c) Consulta los monopatines con más de X viajes en un cierto año.
    @GetMapping("/scooters/trip/{year}")
    public ResponseEntity<Scooter> getTrip(@PathVariable Integer year){
        List<Scooter> scootersByYear = scooterFeignClient.getScootersByYear(year);
        if (scootersByYear == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((Scooter) scootersByYear);
    }

    // 3.d) Como administrador quiero consultar el total facturado en un rango de meses de cierto año.
    @GetMapping("/scooters/trip/{year}/{month1}/{month2}")
    public Integer getTotalFacturado(@PathVariable Integer year, @PathVariable Integer month1, @PathVariable Integer month2){
        Integer totalFacturadoByMonthRange = tripFeignClient.getTotalMontoByDate(year,month1,month2);
        if (totalFacturadoByMonthRange == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(totalFacturadoByMonthRange);
    }

    // 3.e) Como administrador quiero consultar la cantidad de monopatines actualmente en operación, versus la cantidad de monopatines actualmente en mantenimiento.
    @GetMapping("/scooters")
    public List<Integer> getQuantityScooter(){
        List<Integer> quantityScooter = scooterFeignClient.getQuantityScooter();
        if (quantityScooter == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quantityScooter);
    }

    // 3.f) Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema habilite los nuevos precios.
    @PutMapping("/updatePrice/{normalPrice}/{extraPrice}/{date}")
    public void updatePrices(@PathVariable Integer normalPrice,@PathVariable Integer extraPrice,@PathVariable String date){
        adminService.updatePricesInDate(normalPrice,extraPrice,date);
    }


}
