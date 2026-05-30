package edu.marcio.stock.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, name = "corporate_name", unique = true)
    private String corporateName;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false, name = "is_active")
    private boolean isActive;

    @OneToMany
    @JoinColumn(name = "supplier_products", nullable = false)
    private List<Product> products = new ArrayList<>();

}
