package org.youcode.citronix.services.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.youcode.citronix.common.exceptions.EntityNotFoundException;
import org.youcode.citronix.common.services.GenericServiceImpl;
import org.youcode.citronix.dtos.request.TreeRequestDTO;
import org.youcode.citronix.dtos.response.TreeResponseDTO;
import org.youcode.citronix.entities.Field;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.mappers.TreeMapper;
import org.youcode.citronix.repositories.FieldRepository;
import org.youcode.citronix.repositories.TreeRepository;
import org.youcode.citronix.services.TreeService;

@Service
@Transactional
@Validated
public class TreeServiceImpl extends GenericServiceImpl<Tree, Long, TreeRequestDTO, TreeResponseDTO> implements TreeService {
    private final FieldRepository fieldRepository;
    private final TreeRepository treeRepository;
    public TreeServiceImpl(TreeRepository repository, TreeMapper mapper, FieldRepository fieldRepository, TreeRepository treeRepository) {
        super(repository, mapper);
        this.fieldRepository = fieldRepository;
        this.treeRepository = treeRepository;
    }

    @Override
    public TreeResponseDTO create(TreeRequestDTO requestDto) {
        Field field = fieldRepository.findById(requestDto.fieldId())
                .orElseThrow(() -> new EntityNotFoundException("Field with Id " + requestDto.fieldId() + " not found"));

        validateDensity(field);
        Tree entity = mapper.toEntity(requestDto);
        entity.setField(field);
        Tree savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    private void validateDensity(Field field) {
        long treeCount = treeRepository.countByFieldId(field.getId());

        if (treeCount >= 100) {
            throw new IllegalArgumentException("Density limit exceeded: A field cannot have more than 100 trees per hectare.");
        }
    }
}
