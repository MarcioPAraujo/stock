package edu.marcio.stock.service;

import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.supplier.SupplierRequest;
import edu.marcio.stock.entity.Supplier;
import edu.marcio.stock.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository suplierRepository;

    public Supplier registSupplier(SupplierRequest request) {
        Supplier supplierToPersist = new Supplier();

        supplierToPersist.setCnpj(request.getCnpj());
        supplierToPersist.setCorporateName(request.getCorporateName());
        supplierToPersist.setProducts(request.getProductIds());

        return suplierRepository.save(supplierToPersist);
    }

}
