package edu.marcio.stock.service;

import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.productBrand.ProductBrandRequest;
import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.repository.ProductBrandRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductBrandService {
    private final ProductBrandRepository productBrandRepository;

    public ProductBrand registeBrand(ProductBrandRequest request) {
        ProductBrand productBrand = new ProductBrand();
        productBrand.setName(request.getName());
        return productBrandRepository.save(productBrand);
    }
}
