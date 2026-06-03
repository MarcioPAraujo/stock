package edu.marcio.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.marcio.stock.entity.ProductBrand;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, String> {

}
