package org.youcode.citronix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.citronix.entities.Tree;

import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {
    long countByFieldId(Long fieldId);
    List<Tree> findByFieldId(Long fieldId);
}
