package org.youcode.citronix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.citronix.entities.Harvest;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}
