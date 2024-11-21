package tpe.microserviciotrip.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import tpe.microserviciotrip.dto.ReportTripDTO;
import tpe.microserviciotrip.dto.ScooterMinTripsDTO;
import tpe.microserviciotrip.entity.TripEntity;
import tpe.microserviciotrip.service.TripService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TripControllerTest {

    @Mock
    private TripService tripService;

    @InjectMocks
    private TripController tripController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTrips_ShouldReturnListOfTrips() {
        List<TripEntity> trips = Arrays.asList(new TripEntity(), new TripEntity());
        when(tripService.getAll()).thenReturn(trips);

        ResponseEntity<List<TripEntity>> response = tripController.getAllTrips();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void getAllTrips_ShouldReturnNoContentWhenEmpty() {
        when(tripService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<TripEntity>> response = tripController.getAllTrips();

        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void getTripById_ShouldReturnTrip() {
        TripEntity trip = new TripEntity();
        when(tripService.findById(1L)).thenReturn(trip);

        ResponseEntity<TripEntity> response = tripController.getTripById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(trip, response.getBody());
    }

    @Test
    void getTripById_ShouldReturnNotFound() {
        when(tripService.findById(1L)).thenReturn(null);

        ResponseEntity<TripEntity> response = tripController.getTripById(1L);

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void save_ShouldReturnSavedTrip() {
        TripEntity trip = new TripEntity();
        when(tripService.save(trip)).thenReturn(trip);

        ResponseEntity<TripEntity> response = tripController.save(trip);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(trip, response.getBody());
    }

    @Test
    void deleteTripById_ShouldReturnNoContentWhenExists() {
        when(tripService.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = tripController.deleteTripById(1L);

        verify(tripService, times(1)).deleteById(1L);
        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void deleteTripById_ShouldReturnNotFoundWhenNotExists() {
        when(tripService.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> response = tripController.deleteTripById(1L);

        verify(tripService, never()).deleteById(anyLong());
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void getTotalInvoicedByDate_ShouldReturnTotalInvoiced() {
        when(tripService.calculateTotalInvoiced(2023, 1, 3)).thenReturn(1000);

        ResponseEntity<?> response = tripController.getTotalInvoicedByDate(2023, 1, 3);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1000, response.getBody());
    }

    @Test
    void getReportKmByScooters_ShouldReturnReportList() {
        List<ReportTripDTO> reportList = Arrays.asList(
                new ReportTripDTO(1L, 100.5),
                new ReportTripDTO(2L, 200.0)
        );
        when(tripService.getReportKmByScooters()).thenReturn(reportList);

        ResponseEntity<List<ReportTripDTO>> response = tripController.getReportKmByScooters();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
        assertEquals(100.5, response.getBody().get(0).getTotalDistance());
    }

    @Test
    void getScootersWithMinTrips_ShouldReturnListOfScooters() {
        List<ScooterMinTripsDTO> scooterList = Arrays.asList(new ScooterMinTripsDTO(), new ScooterMinTripsDTO());
        when(tripService.findScootersWithMinTripsInYear(2023, 10)).thenReturn(scooterList);

        List<ScooterMinTripsDTO> response = tripController.getScootersWithMinTrips(2023, 10);

        assertEquals(2, response.size());
    }
}
