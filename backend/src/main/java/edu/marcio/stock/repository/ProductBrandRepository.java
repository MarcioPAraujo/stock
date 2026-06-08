package edu.marcio.stock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.marcio.stock.entity.ProductBrand;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, String> {

    ProductBrand findByName(String name);

    @NativeQuery("SELECT * FROM product_brand p " +
            "WHERE :name IS NULL OR " +
            "unaccent(LOWER(p.name)) " +
            "LIKE unaccent(LOWER(CONCAT('%', :name, '%')))")
    Page<ProductBrand> findBrandPage(Pageable pageable, @Param("name") String name);
}
