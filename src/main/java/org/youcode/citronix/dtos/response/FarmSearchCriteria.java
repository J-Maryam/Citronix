package org.youcode.citronix.dtos.response;

import java.time.LocalDate;

public record FarmSearchCriteria(
        String name,
        String location,
        Double minArea,
        Double maxArea,
        LocalDate creationDateAfter,
        LocalDate creationDateBefore
) {
}
