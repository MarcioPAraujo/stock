package edu.marcio.stock.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.sector.SectorRequest;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.exceptions.ResourceAlreadyRegisteredException;
import edu.marcio.stock.repository.SectorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;

    public Sector registerSector(SectorRequest request) {
        Optional<Sector> optionalSector = sectorRepository.findByName(request.getName());

        if (optionalSector.isPresent()) {
            throw new ResourceAlreadyRegisteredException("This sector is already registered!");
        }

        Sector sector = new Sector();
        sector.setName(request.getName());

        Optional<Sector> optionalSavedSector = Optional.ofNullable(sectorRepository.save(sector));

        if (optionalSavedSector.isEmpty()) {
            throw new RuntimeException("fail in save the sector");
        }
        return optionalSavedSector.get();
    }
}
