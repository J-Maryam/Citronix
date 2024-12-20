package org.youcode.citronix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.citronix.entities.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
