package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.product.ProductRequest;
import edu.marcio.stock.dto.product.ProductResponse;
import edu.marcio.stock.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> registerNewProduct(@Valid @RequestBody ProductRequest body) {
        ProductResponse response = productService.registerProduct(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
