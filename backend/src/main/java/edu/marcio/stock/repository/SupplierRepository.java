package edu.marcio.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.marcio.stock.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {

}
