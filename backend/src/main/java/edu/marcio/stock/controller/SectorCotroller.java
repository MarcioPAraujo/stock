package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.sector.SectorListingParamsRequest;
import edu.marcio.stock.dto.sector.SectorProductsListingParamsRequest;
import edu.marcio.stock.dto.sector.SectorRequest;
import edu.marcio.stock.entity.Product;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.service.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sectors")
public class SectorCotroller {

    private final SectorService sectorService;

    @PostMapping
    public ResponseEntity<Sector> createSector(@Valid @RequestBody SectorRequest body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sectorService.registerSector(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sector> updateSectorName(@PathVariable String id, @Valid @RequestBody SectorRequest body) {
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.editSectorName(body, id));
    }

    @GetMapping
    public ResponseEntity<Page<Sector>> getPaginatedSectors(Pageable pageable,
            @ModelAttribute SectorListingParamsRequest params) {
        Page<Sector> sectorPage = sectorService.getSectorPage(pageable, params);

        return ResponseEntity.status(HttpStatus.OK).body(sectorPage);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<Product>> getSectorProducts(@PathVariable String id, Pageable pageable,
            @ModelAttribute SectorProductsListingParamsRequest params) {
        Page<Product> sectorProductsPage = sectorService.getSectorProductsPage(id, pageable, params);

        return ResponseEntity.status(HttpStatus.OK).body(sectorProductsPage);
    }

}
