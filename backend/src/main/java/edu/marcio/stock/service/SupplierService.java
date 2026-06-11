package edu.marcio.stock.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.marcio.stock.dto.supplier.SupplierRequest;
import edu.marcio.stock.entity.Supplier;
import edu.marcio.stock.exceptions.ResourceNotFoundException;
import edu.marcio.stock.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Transactional
    public Supplier registSupplier(SupplierRequest request) {
        Supplier supplierToPersist = new Supplier();

        supplierToPersist.setCnpj(request.getCnpj());
        supplierToPersist.setCorporateName(request.getCorporateName());
        supplierToPersist.setProducts(request.getProductIds());

        return supplierRepository.save(supplierToPersist);
    }

    public Page<Supplier> getSupplierPage(Pageable pageable, String name) {
        return supplierRepository.findPageWithNameFilter(pageable, name);
    }

    @Transactional
    public Supplier toggleSupplierStatus(String id) {
        Supplier supplier = supplierRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("The supplier with id %s was not found", id)));

        supplier.setActive(!supplier.isActive());

        return supplierRepository.save(supplier);
    }

}
