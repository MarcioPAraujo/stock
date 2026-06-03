package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.productBrand.ProductBrandRequest;
import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.service.ProductBrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brand")
public class ProductBrandController {

    private final ProductBrandService productBrandService;

    @PostMapping
    public ResponseEntity<ProductBrand> registerNewBrand(@RequestBody @Valid ProductBrandRequest body) {
        ProductBrand brand = productBrandService.registeBrand(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }

}
