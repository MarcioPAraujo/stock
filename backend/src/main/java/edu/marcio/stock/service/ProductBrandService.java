package edu.marcio.stock.service;

import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.productBrand.ProductBrandRequest;
import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.exceptions.DuplicatedResourcesException;
import edu.marcio.stock.repository.ProductBrandRepository;
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
}
