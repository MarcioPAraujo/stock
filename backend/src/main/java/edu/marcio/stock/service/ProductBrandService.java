package edu.marcio.stock.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.productBrand.ProductBrandRequest;
import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.exceptions.DuplicatedResourcesException;
import edu.marcio.stock.exceptions.ResourceNotFoundException;
import edu.marcio.stock.repository.ProductBrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductBrandService {
    private final ProductBrandRepository productBrandRepository;

    public ProductBrand registeBrand(ProductBrandRequest request) {

        ProductBrand brand = productBrandRepository.findByName(request.getName().toUpperCase());

        if (brand != null) {
            throw new DuplicatedResourcesException(
                    String.format("The brand %s is already registered", request.getName()));
        }

        ProductBrand productBrand = new ProductBrand();
        productBrand.setName(request.getName().toUpperCase());
        return productBrandRepository.save(productBrand);
    }

    public Page<ProductBrand> getProductPage(Pageable pageable, String name) {
        return productBrandRepository.findBrandPage(pageable, name);
    }

    @Transactional
    public ProductBrand togleStatus(String id) {
        ProductBrand brand = productBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Brand with id %s not found", id)));

        brand.setActive(!brand.isActive());

        return productBrandRepository.save(brand);
    }
}
