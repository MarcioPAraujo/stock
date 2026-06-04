package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.productBrand.ProductBrandRequest;
import edu.marcio.stock.entity.Product;
import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.service.ProductBrandService;
import edu.marcio.stock.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class ProductBrandController {

    private final ProductBrandService productBrandService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductBrand> registerNewBrand(@RequestBody @Valid ProductBrandRequest body) {
        ProductBrand brand = productBrandService.registeBrand(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<Page<Product>> getMethodName(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @PageableDefault(sort = "name") Pageable pageable) {

        Page<Product> productPage = productService.getProductsFromBrand(id, name, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productPage);
    }

}
