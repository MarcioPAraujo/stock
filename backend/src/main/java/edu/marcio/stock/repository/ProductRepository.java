package edu.marcio.stock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.marcio.stock.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT DISTINCT p FROM Product p " +
            "JOIN p.sectors s " +
            "LEFT JOIN p.brand b " +
            "WHERE s.id = :id " +
            "AND (:productName IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :productName, '%'))) " +
            "AND (:brandId IS NULL OR b.id = :brandId) " +
            "AND (:sku IS NULL OR p.sku = :sku) " +
            "AND (:ean IS NULL OR p.ean = :ean) " +
            "AND (:isActive IS NULL OR p.isActive = :isActive)")
    Page<Product> findAllProductsBySectorIdWithFilters(
            Pageable pageable,
            @Param("id") String id,
            @Param("productName") String productName,
            @Param("sku") String sku,
            @Param("ean") String ean,
            @Param("brandId") String brandId,
            @Param("isActive") boolean isActive);
}
