package edu.marcio.stock.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.marcio.stock.dto.product.ProductRequest;
import edu.marcio.stock.dto.product.ProductResponse;
import edu.marcio.stock.entity.Product;
import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.exceptions.ResourceNotFoundException;
import edu.marcio.stock.repository.ProductBrandRepository;
import edu.marcio.stock.repository.ProductRepository;
import edu.marcio.stock.repository.SectorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SectorRepository sectorRepository;
    private final ProductBrandRepository productBrandRepository;

    @Transactional
    public ProductResponse registerProduct(ProductRequest request) {

        ProductBrand productBrand = productBrandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("The provided brand was not founded"));

        List<Sector> secotorsList = sectorRepository.findAllById(request.getSectorsId());

        if (secotorsList.isEmpty() || secotorsList.size() != request.getSectorsId().size()) {
            throw new ResourceNotFoundException("One or more provided sectors were not found");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setBrand(productBrand);
        product.setEan(request.getEan());
        product.setPrice(request.getPrice());
        product.setSectors(secotorsList);
        product.setMeasureType(product.getMeasureType());

        Product savedProduct = productRepository.save(product);
        return new ProductResponse(savedProduct);
    }
}
