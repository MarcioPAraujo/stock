package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.supplier.SupplierRequest;
import edu.marcio.stock.entity.Supplier;
import edu.marcio.stock.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/register")
    public ResponseEntity<Supplier> registerNewSupplier(@Valid @RequestBody SupplierRequest bodyRequest) {
        Supplier supplier = supplierService.registSupplier(bodyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplier);
    }

}
