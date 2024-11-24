package org.youcode.citronix.services.Impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableFarmResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableHarvestResponseDTO;
import org.youcode.citronix.dtos.embeddableResponseDTO.EmbeddableTreeResponseDTO;
import org.youcode.citronix.dtos.request.FieldRequestDTO;
import org.youcode.citronix.dtos.response.FieldResponseDTO;
import org.youcode.citronix.entities.Farm;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.mappers.FieldMapper;
import org.youcode.citronix.repositories.FarmRepository;
import org.youcode.citronix.repositories.FieldRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private FieldMapper fieldMapper;

    @InjectMocks
    private FieldServiceImpl fieldService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createField_ShouldFail_WhenFarmNotFound() {
        FieldRequestDTO requestDTO = new FieldRequestDTO(999L, 20L);

        when(farmRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> fieldService.create(requestDTO));
        verify(fieldRepository, never()).save(any());
    }

    @Test
    void testCreateField_FarmNotFound() {
        FieldRequestDTO requestDto = new FieldRequestDTO(5.0, 999L);
        when(farmRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> fieldService.create(requestDto));
    }

    @Test
    void createField_ShouldFail_WhenAreaIsTooSmall() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100);
        farm.setFields(List.of());

        FieldRequestDTO requestDTO = new FieldRequestDTO(0.05, 1L);

        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        assertThrows(IllegalArgumentException.class, () -> fieldService.create(requestDTO));
    }

    @Test
    void createField_ShouldFail_WhenAreaExceeds50PercentOfFarm() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100);
        farm.setFields(List.of());

        FieldRequestDTO requestDTO = new FieldRequestDTO(60, 1L);

        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        assertThrows(IllegalArgumentException.class, () -> fieldService.create(requestDTO));
    }

    @Test
    void createField_ShouldFail_WhenMoreThan10Fields() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100);
        List<Field> fields = List.of(new Field(), new Field(), new Field(), new Field(), new Field(),
                new Field(), new Field(), new Field(), new Field(), new Field());
        farm.setFields(fields);

        FieldRequestDTO requestDTO = new FieldRequestDTO(5, 1L);

        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        assertThrows(IllegalArgumentException.class, () -> fieldService.create(requestDTO));
    }

    @Test
    void updateField_ShouldSucceed_WhenDataIsValid() {
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(100);
        farm.setFields(List.of());

        Field existingField = new Field();
        existingField.setId(1L);
        existingField.setArea(15);
        existingField.setFarm(farm);

        FieldRequestDTO updateDTO = new FieldRequestDTO(25, 1L);

        EmbeddableFarmResponseDTO embeddableFarmResponseDTO = new EmbeddableFarmResponseDTO(1L, null, null, 100, null);
        List<EmbeddableTreeResponseDTO> embeddableTreeResponseDTOs = List.of();
        List<EmbeddableHarvestResponseDTO> embeddableHarvestResponseDTOs = List.of();

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(existingField));
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));
        when(fieldRepository.save(existingField)).thenReturn(existingField);

        when(fieldMapper.toDto(existingField)).thenReturn(new FieldResponseDTO(
                1L,
                25.0,
                embeddableFarmResponseDTO,
                embeddableTreeResponseDTOs,
                embeddableHarvestResponseDTOs
        ));

        FieldResponseDTO result = fieldService.update(1L, updateDTO);

        assertNotNull(result);
        assertEquals(25, result.area());
        verify(fieldRepository, times(1)).save(existingField);
    }

    @Test
    void updateField_ShouldFail_WhenFieldNotFound() {
        FieldRequestDTO updateDTO = new FieldRequestDTO(1L, 25L);

        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> fieldService.update(1L, updateDTO));
        verify(fieldRepository, never()).save(any());
    }

    @Test
    void updateField_ShouldFail_WhenFarmNotFound() {
        Field existingField = new Field();
        existingField.setId(1L);
        existingField.setArea(15);

        FieldRequestDTO updateDTO = new FieldRequestDTO(25, 999L);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(existingField));
        when(farmRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> fieldService.update(1L, updateDTO));
    }

    @Test
    void deleteField_ShouldSucceed_WhenFieldExists() {
        Field existingField = new Field();
        existingField.setId(1L);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(existingField));
        doNothing().when(fieldRepository).delete(existingField);

        fieldService.delete(1L);

        verify(fieldRepository, times(1)).delete(existingField);
    }

    @Test
    void deleteField_ShouldFail_WhenFieldNotFound() {
        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> fieldService.delete(1L));
        verify(fieldRepository, never()).delete(any());
    }
}
