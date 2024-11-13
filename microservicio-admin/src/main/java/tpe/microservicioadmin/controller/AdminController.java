package tpe.microservicioadmin.controller;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpe.microservicioadmin.dto.ReportTripDTO;
import tpe.microservicioadmin.feignClients.ScooterFeignClient;
import tpe.microservicioadmin.feignClients.StationFeignClient;
import tpe.microservicioadmin.feignClients.TripFeignClient;
import tpe.microservicioadmin.model.Scooter;
import tpe.microservicioadmin.model.Station;
import tpe.microservicioadmin.service.AdminService;
import tpe.microservicioadmin.entity.AdminEntity;
import tpe.microservicioadmin.feignClients.UserFeignClient;
import tpe.microservicioscooter.dto.ScooterQuantityDTO;
import java.time.LocalDate;
import java.util.List;

@Slf4j
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
    @Autowired
    TripFeignClient  tripFeignClient;

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

    @PostMapping("/addAdmin")
    public ResponseEntity<AdminEntity> save(@RequestBody AdminEntity adm) {
        AdminEntity savedAdm = adminService.save(adm);
        return ResponseEntity.ok(savedAdm);
    }

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

    //Registrar fin del mantenimietno del monopatin
    @PutMapping("/scooter/finishMaintenance/{id}")
    public ResponseEntity<Scooter> finishMaintenance(@PathVariable Long id) {
        try {
            ResponseEntity<Scooter> response = scooterFeignClient.finishMaintenance(id);
            return response;
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Eliminar un parada
    @DeleteMapping("/stations/deleteStation/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        try {
            stationFeignClient.delete(id);
            return ResponseEntity.noContent().build();
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Agregar parada
    @PostMapping("/stations/addStation")
    public ResponseEntity<Station> addStation(@RequestBody Station station) {
        ResponseEntity<Station> respuesta = stationFeignClient.save(station);
        return respuesta;
    }

    //Generar reporte de uso de monopatines por kilómetros
    @GetMapping("/trips/reportTripsByScooter")
    public ResponseEntity<List<ReportTripDTO>> getReportTripsByScooter(){
        List<ReportTripDTO> reportTripsByScooter = tripFeignClient.getReportTripsByScooter();
        if (reportTripsByScooter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reportTripsByScooter);
    }

    // 3.b) Cambiar estado de cuenta
    @PutMapping("/users/active/{id}")
    public ResponseEntity<String> setActive(@PathVariable Long id){
        try {
            userFeignClient.inactive(id);
            return ResponseEntity.ok("Usuario deshabilitado");
        } catch (FeignException e) {
            return ResponseEntity.status(415).body("Error");
        }
    }

    // 3.c) Consulta los monopatines con más de X viajes en un cierto año.
    @GetMapping("/scooters/mostTrips")
    public ResponseEntity<List<Scooter>> getScootersWithMostTrips(@RequestParam("year") int year, @RequestParam("minTrips") int minTrips) {
        List<Long> scooterIds = tripFeignClient.getScooterIdsWithMinTripsInYear(year, minTrips);
        if (scooterIds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Scooter> scooters = scooterFeignClient.getScootersByIds(scooterIds);
        return ResponseEntity.ok(scooters);
    }
    // 3.d) Como administrador quiero consultar el total facturado en un rango de meses de cierto año.
    @GetMapping("/trips/totalInvoiced")
    public ResponseEntity<Integer> getTotalInvoiced(@RequestParam Integer year, @RequestParam Integer month1, @RequestParam Integer month2){
        Integer totalInvoicedByMonth = tripFeignClient.getTotalInvoicedByDate(year, month1, month2);
        if (totalInvoicedByMonth != null) {
            return ResponseEntity.ok(totalInvoicedByMonth);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3.e) Como administrador quiero consultar la cantidad de monopatines actualmente en operación, versus la cantidad de monopatines actualmente en mantenimiento.
    @GetMapping("/scooters/quantity")
    public ResponseEntity<ScooterQuantityDTO> getQuantityScooter(){
        ScooterQuantityDTO quantityScooter = scooterFeignClient.getQuantityScooter();
        if (quantityScooter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quantityScooter);
    }

    // 3.f) Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema habilite los nuevos precios.
    @PutMapping("/pricing/{id}/{normalPrice}/{extraPrice}")
    public void updatePricesInDate(@PathVariable Long id, @PathVariable Integer normalPrice,@PathVariable Integer extraPrice,@RequestBody LocalDate date){
        LocalDate today = LocalDate.now();
        if (date.equals(today)) {
            // Si la fecha es hoy, actualizar los precios
            adminService.updatePricesInDate(id, normalPrice, extraPrice);
        } else {
            // Si la fecha no es hoy, se debe actualizar los precios recién en esa fecha. Hacer un Scheduler??
            adminService.updatePricesInDate(id, normalPrice, extraPrice);
        }
    }


}