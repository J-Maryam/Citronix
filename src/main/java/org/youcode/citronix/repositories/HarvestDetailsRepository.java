package org.youcode.citronix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.youcode.citronix.entities.HarvestDetail;
import org.youcode.citronix.entities.Tree;
import org.youcode.citronix.entities.embeddable.HarvestDetailId;
import org.youcode.citronix.entities.enums.Season;


@Repository
public interface HarvestDetailsRepository extends JpaRepository<HarvestDetail, HarvestDetailId> {
    boolean existsByTreeAndHarvest_Season(Tree tree, Season season);
}
