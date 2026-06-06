package edu.marcio.stock.specification;

import org.springframework.data.jpa.domain.Specification;

import edu.marcio.stock.entity.Sector;

public class SectorSpecification {
    public static Specification<Sector> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

}
