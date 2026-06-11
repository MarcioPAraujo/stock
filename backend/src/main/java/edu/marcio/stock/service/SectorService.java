package edu.marcio.stock.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.marcio.stock.dto.sector.SectorListingParamsRequest;
import edu.marcio.stock.dto.sector.SectorProductsListingParamsRequest;
import edu.marcio.stock.dto.sector.SectorRequest;
import edu.marcio.stock.entity.Product;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.exceptions.ResourceAlreadyRegisteredException;
import edu.marcio.stock.exceptions.ResourceNotFoundException;
import edu.marcio.stock.repository.ProductRepository;
import edu.marcio.stock.repository.SectorRepository;
import edu.marcio.stock.specification.SectorSpecification;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectorService {
    private final SectorRepository sectorRepository;
    private final ProductRepository productRepository;

    private void isSectorAlreadyRegistered(String name) {
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

    public Page<Sector> getSectorPage(Pageable pageable, SectorListingParamsRequest params) {
        Specification<Sector> sectorSpecification = Specification.where(SectorSpecification.hasName(params.getName()));

        return sectorRepository.findAll(sectorSpecification, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Product> getSectorProductsPage(
            String id,
            Pageable pageable,
            SectorProductsListingParamsRequest params) {

        System.out.println(params.getProductName());

        Page<Product> productPage = productRepository.findAllProductsBySectorIdWithFilters(
                pageable,
                id,
                params.getProductName(),
                params.getSku(),
                params.getEan(),
                params.getBrand(),
                params.getIsActive());

        return productPage;
    }

}
