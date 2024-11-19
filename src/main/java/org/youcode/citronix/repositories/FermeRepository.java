package org.youcode.citronix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.citronix.entities.Farm;

@Repository
public interface FermeRepository extends JpaRepository<Farm, Long> {
}
