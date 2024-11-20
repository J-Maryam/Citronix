package org.youcode.citronix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.citronix.entities.Field;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
}
