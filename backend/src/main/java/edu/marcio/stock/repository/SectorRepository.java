package edu.marcio.stock.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import edu.marcio.stock.entity.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, String>, JpaSpecificationExecutor<Sector> {
    Optional<Sector> findByName(String name);

    Page<Sector> findAll(Pageable pageable);
}
