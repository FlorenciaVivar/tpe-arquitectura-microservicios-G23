package tpe.microserviciotrip;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    tpe.microserviciotrip.TripService tripService;

    @GetMapping("/")
    public ResponseEntity<List<tpe.microserviciotrip.Trip>> getAllTrips() {
        List<tpe.microserviciotrip.Trip> t = tripService.getAll();
        if (t.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(t);
    }

    @GetMapping("/{id}")
    public ResponseEntity<tpe.microserviciotrip.Trip> getTripById(@PathVariable("id") Long id) {
        Trip t = tripService.findById(id);
        if (t == null) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t);
    }

    @PostMapping("")
    public ResponseEntity<Trip> save(@RequestBody Trip T) {
        tripEntity e.t = tripService.save(t);
        return ResponseEntity.ok(e.t);
    }
}