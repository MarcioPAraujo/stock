package edu.marcio.stock.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.sector.SectorRequest;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.exceptions.ResourceAlreadyRegisteredException;
import edu.marcio.stock.exceptions.ResourceNotFoundException;
import edu.marcio.stock.repository.SectorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;

    public void isSectorAlreadyRegistered(String name) {
        Optional<Sector> optionalSector = sectorRepository.findByName(name);

        if (optionalSector.isPresent()) {
            throw new ResourceAlreadyRegisteredException("This sector is already registered!");
        }
    }

    public Sector registerSector(SectorRequest request) {

        isSectorAlreadyRegistered(request.getName());

        Sector sector = new Sector();
        sector.setName(request.getName());

        Optional<Sector> optionalSavedSector = Optional.ofNullable(sectorRepository.save(sector));

        if (optionalSavedSector.isEmpty()) {
            throw new RuntimeException("fail in save the sector");
        }
        return optionalSavedSector.get();
    }

    public Sector editSectorName(SectorRequest request, String id) {
        Optional<Sector> optionalSector = sectorRepository.findById(id);

        if (optionalSector.isEmpty()) {
            throw new ResourceNotFoundException(String.format("the sector with id %s, was not founded", id));
        }

        isSectorAlreadyRegistered(request.getName());

        Sector sector = optionalSector.get();
        sector.setName(request.getName());

        return sectorRepository.save(sector);
    }

}
