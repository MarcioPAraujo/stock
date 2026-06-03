package edu.marcio.stock.service;

import org.springframework.stereotype.Service;

import edu.marcio.stock.entity.Product;
import edu.marcio.stock.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

}
