package edu.marcio.stock.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.marcio.stock.dto.supplier.SupplierRequest;
import edu.marcio.stock.entity.Supplier;
import edu.marcio.stock.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;

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

}
