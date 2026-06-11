package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.product.ProductRequest;
import edu.marcio.stock.dto.product.ProductResponse;
import edu.marcio.stock.entity.Product;
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
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/list")
    public ResponseEntity<Page<Product>> getProductPageList(
            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean isActive) {
        Page<Product> productPage = productService.getProductsWithIsActiveAndNameFilter(pageable, name, isActive);
        return ResponseEntity.status(HttpStatus.OK).body(productPage);
    }

}
