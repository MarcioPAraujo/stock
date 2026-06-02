package edu.marcio.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.marcio.stock.entity.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, String> {
    Optional<Sector> findByName(String name);
}
