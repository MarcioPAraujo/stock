package edu.marcio.stock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.marcio.stock.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    @NativeQuery("SELECT * FROM supplier s " +
            "WHERE :name IS NULL OR unaccent(LOWER(s.corporate_name)) " +
            "LIKE unaccent(LOWER(CONCAT('%', :name, '%'))) " +
            "ORDER BY s.corporate_name")
    Page<Supplier> findPageWithNameFilter(Pageable pageable, @Param("name") String name);
}
