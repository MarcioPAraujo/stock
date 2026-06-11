package edu.marcio.stock.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.marcio.stock.dto.product.ProductRequest;
import edu.marcio.stock.dto.product.ProductResponse;
import edu.marcio.stock.entity.Product;
import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.entity.Sector;
import edu.marcio.stock.entity.Supplier;
import edu.marcio.stock.enums.ProductMeasure;
import edu.marcio.stock.exceptions.ResourceNotFoundException;
import edu.marcio.stock.repository.ProductBrandRepository;
import edu.marcio.stock.repository.ProductRepository;
import edu.marcio.stock.repository.SectorRepository;
import edu.marcio.stock.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SectorRepository sectorRepository;
    private final ProductBrandRepository productBrandRepository;
    private final SupplierRepository supplierRepository;

    private ProductMeasure parseStringToMeasure(String measure) {
        if (measure == null || measure.isBlank()) {
            return null;
        }
        try {
            return ProductMeasure.valueOf(measure.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("The value os %s is not a valid measure", measure));
        }
    }

    @Transactional
    public ProductResponse registerProduct(ProductRequest request) {

        ProductBrand productBrand = productBrandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("The provided brand was not founded"));

        List<Sector> secotorsList = sectorRepository.findAllById(request.getSectorsId());

        if (secotorsList.isEmpty() || secotorsList.size() != request.getSectorsId().size()) {
            throw new ResourceNotFoundException("One or more provided sectors were not found");
        }

        Supplier requestedSupplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The supplier with id %s was not found", request.getSupplierId())));

        Product product = new Product();
        product.setName(request.getName());
        product.setBrand(productBrand);
        product.setEan(request.getEan());
        product.setPrice(request.getPrice());
        product.setSectors(secotorsList);
        product.setMeasureType(parseStringToMeasure(request.getMeasureType()));
        product.setSupplier(requestedSupplier);

        Product savedProduct = productRepository.save(product);
        return new ProductResponse(savedProduct);
    }

    public Page<Product> getProductsFromBrand(String brandId, String productName, Pageable pageable) {
        return productRepository.findByBrandIdAndName(pageable, brandId, productName);
    }

    public Page<Product> getProductsWithIsActiveAndNameFilter(
            Pageable pageable,
            String name,
            Boolean isActive) {
        Page<Product> productPage = productRepository.findPageWithNameOrIsActiveFilter(pageable, name, isActive);
        return productPage;
    }
}
