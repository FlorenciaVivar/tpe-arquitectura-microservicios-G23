package tpe.microservicioscooter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import tpe.microservicioscooter.dto.ScooterKilometerDTO;
import tpe.microservicioscooter.dto.ScooterQuantityDTO;
import tpe.microservicioscooter.entities.ScooterEntity;
import tpe.microservicioscooter.service.ScooterService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScooterControllerTest {

    @InjectMocks
    private ScooterController scooterController;

    @Mock
    private ScooterService scooterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllScooters_ReturnsListOfScooters() {
        ScooterEntity scooter1 = new ScooterEntity(1L, "available", true, 100.0, "stop1", 10);
        ScooterEntity scooter2 = new ScooterEntity(2L, "available", true, 150.0, "stop2", 15);
        List<ScooterEntity> scooters = Arrays.asList(scooter1, scooter2);

        when(scooterService.getAll()).thenReturn(scooters);

        ResponseEntity<List<ScooterEntity>> response = scooterController.getAllScooters();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
        verify(scooterService, times(1)).getAll();
    }

    @Test
    void testGetAllScooters_EmptyList_ReturnsNoContent() {
        List<ScooterEntity> scooters = Collections.emptyList();

        when(scooterService.getAll()).thenReturn(scooters);

        ResponseEntity<List<ScooterEntity>> response = scooterController.getAllScooters();

        assertEquals(204, response.getStatusCode().value());
        verify(scooterService, times(1)).getAll();
    }

    @Test
    void testGetScooterById_Found_ReturnsScooter() {
        ScooterEntity scooter = new ScooterEntity(1L, "available", true, 100.0, "stop1", 10);

        when(scooterService.findById(1L)).thenReturn(scooter);

        ResponseEntity<ScooterEntity> response = scooterController.getScooterById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(scooter, response.getBody());
        verify(scooterService, times(1)).findById(1L);
    }

    @Test
    void testGetScooterById_NotFound_ReturnsNotFound() {
        when(scooterService.findById(1L)).thenReturn(null);

        ResponseEntity<ScooterEntity> response = scooterController.getScooterById(1L);

        assertEquals(404, response.getStatusCode().value());
        verify(scooterService, times(1)).findById(1L);
    }

    @Test
    void testGetAvailableScooters_ReturnsListOfAvailableScooters() {
        ScooterEntity scooter1 = new ScooterEntity(1L, "available", true, 100.0, "stop1", 10);
        List<ScooterEntity> scooters = Arrays.asList(scooter1);

        when(scooterService.getAvailableScooters()).thenReturn(scooters);

        ResponseEntity<List<ScooterEntity>> response = scooterController.getAvailableScooters();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        verify(scooterService, times(1)).getAvailableScooters();
    }

    @Test
    void testGetAvailableScooters_EmptyList_ReturnsNoContent() {
        List<ScooterEntity> scooters = Collections.emptyList();

        when(scooterService.getAvailableScooters()).thenReturn(scooters);

        ResponseEntity<List<ScooterEntity>> response = scooterController.getAvailableScooters();

        assertEquals(204, response.getStatusCode().value());
        verify(scooterService, times(1)).getAvailableScooters();
    }

    @Test
    void testGetScooterKilometerReport_ReturnsKilometerReport() {
        ScooterKilometerDTO dto1 = new ScooterKilometerDTO("1", 100.0);
        ScooterKilometerDTO dto2 = new ScooterKilometerDTO("2", 150.0);
        List<ScooterKilometerDTO> report = Arrays.asList(dto1, dto2);

        when(scooterService.calculateTotalKilometersForScooter()).thenReturn(report);

        ResponseEntity<List<ScooterKilometerDTO>> response = scooterController.getScooterKilometerReport();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
        verify(scooterService, times(1)).calculateTotalKilometersForScooter();
    }

    @Test
    void testGetScootersQuantity_ReturnsScooterQuantity() {
        ScooterQuantityDTO quantityDTO = new ScooterQuantityDTO(5, 3);

        when(scooterService.calculateQuantityScooter()).thenReturn(quantityDTO);

        ResponseEntity<ScooterQuantityDTO> response = scooterController.getScootersQuantity();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(quantityDTO, response.getBody());
        verify(scooterService, times(1)).calculateQuantityScooter();
    }
}
