package edu.marcio.stock.entity;

import java.util.ArrayList;
import java.util.List;

import edu.marcio.stock.enums.ProductMeasure;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    @NotBlank(message = "the EAN of the product must be provided")
    private String ean;

    @Column(nullable = false)
    @NotBlank(message = "the name of the product must be provided")
    private String name;

    @Column
    private String brand;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false, name = "measure_type")
    private ProductMeasure measureType;

    @Column(nullable = false)
    private boolean isActive;

    @PrePersist
    public void setActive() {
        this.isActive = true;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_sector", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private List<Sector> sectors = new ArrayList<>();
}
