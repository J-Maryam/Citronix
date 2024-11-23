package org.youcode.citronix.services.Impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.dtos.request.FarmRequestDTO;
import org.youcode.citronix.dtos.response.FarmResponseDTO;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.mappers.FarmMapper;
import org.youcode.citronix.repositories.FarmRepository;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FarmServiceImplTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FarmMapper farmMapper;

    @InjectMocks
    private FarmServiceImpl farmService;

    private FarmRequestDTO validFarmRequestDTO;
    private FarmRequestDTO invalidFarmRequestDTO;
    private FarmRequestDTO smallAreaFarmRequestDTO;
    private Farm farm;
    private FarmResponseDTO farmResponseDTO;

    @BeforeEach
    void setUp() {
        validFarmRequestDTO = new FarmRequestDTO("Farm 1", "Location 1", 1.0, null);
        invalidFarmRequestDTO = new FarmRequestDTO("Farm 2", "Location 2", 0.1, null);
        smallAreaFarmRequestDTO = new FarmRequestDTO("Small Area Farm", "Location 2", 0.1, LocalDate.now());
        farm = new Farm(1L, "Farm 1", "Location 1", 1.0, LocalDate.now(), null);
        farmResponseDTO = new FarmResponseDTO(1L, "Farm 1", "Location 1", 1.0, null, null);

        lenient().when(farmMapper.toEntity(validFarmRequestDTO)).thenReturn(farm);
        lenient().when(farmMapper.toDto(farm)).thenReturn(farmResponseDTO);

    }

    @Test
    void testCreateFarmSuccess() {
        when(farmRepository.save(any(Farm.class))).thenReturn(farm);
        FarmResponseDTO result = farmService.create(validFarmRequestDTO);

        assertNotNull(result);
        assertEquals("Farm 1", result.name());
        assertEquals("Location 1", result.location());
        assertEquals(1.0, result.area());
        verify(farmRepository).save(any(Farm.class));
    }

    @Test
    void testGetFarmSuccess() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        FarmResponseDTO result = farmService.getById(1L);

        assertNotNull(result);
        assertEquals("Farm 1", result.name());
        assertEquals("Location 1", result.location());
        verify(farmRepository).findById(1L);
    }

    @Test
    void testGetFarmNotFound() {
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            farmService.getById(1L);
        });

        verify(farmRepository).findById(1L);
    }

    @Test
    void testUpdateFarmNotFound() {
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            farmService.update(1L, validFarmRequestDTO);
        });
        verify(farmRepository, never()).save(any(Farm.class));
    }

    @Test
    void testCreateFarmWithSmallArea() {
        assertThrows(IllegalArgumentException.class, () -> {
            farmService.create(smallAreaFarmRequestDTO);
        });
        verify(farmRepository, never()).save(any(Farm.class));
    }

    @Test
    void testUpdateNonExistentFarm() {
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());

        FarmRequestDTO updateRequest = new FarmRequestDTO("Non-Existent Farm", "Location", 1.0, null);

        assertThrows(EntityNotFoundException.class, () -> {
            farmService.update(1L, updateRequest);
        });
        verify(farmRepository, never()).save(any(Farm.class));
    }

    @Test
    void testUpdateFarmWithInvalidArea() {
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        assertThrows(IllegalArgumentException.class, () -> {
            farmService.update(1L, invalidFarmRequestDTO);
        });
        verify(farmRepository, never()).save(any(Farm.class));
    }

    @Test
    void testDeleteFarmNotFound() {
        when(farmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            farmService.delete(1L);
        });
        verify(farmRepository, never()).deleteById(anyLong());
    }
}
