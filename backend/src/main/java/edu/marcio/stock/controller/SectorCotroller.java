package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.sector.SectorRequest;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.service.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sector")
public class SectorCotroller {

    private final SectorService sectorService;

    @PostMapping
    public ResponseEntity<Sector> createSector(@Valid @RequestBody SectorRequest body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sectorService.registerSector(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sector> updateSectorName(@PathVariable String id, @RequestBody SectorRequest body) {
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.editSectorName(body, id));
    }

}
