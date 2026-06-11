package edu.marcio.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.marcio.stock.dto.supplier.SupplierRequest;
import edu.marcio.stock.entity.Supplier;
import edu.marcio.stock.service.SupplierService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/list")
    public ResponseEntity<Page<Supplier>> getSupplierPage(@PageableDefault(size = 10, page = 0) Pageable pageable,
            @RequestParam(required = false) String name) {
        Page<Supplier> supplierPage = supplierService.getSupplierPage(pageable, name);
        return ResponseEntity.status(HttpStatus.OK).body(supplierPage);
    }

    @PatchMapping("/toggle-status/{id}")
    public ResponseEntity<Supplier> toggleSupplierStatus(@PathVariable String id) {
        Supplier supplier = supplierService.toggleSupplierStatus(id);
        return ResponseEntity.status(HttpStatus.OK).body(supplier);
    }

}
