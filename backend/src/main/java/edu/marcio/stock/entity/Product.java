package edu.marcio.stock.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import edu.marcio.stock.enums.ProductMeasure;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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

    @Column(nullable = false, unique = true)
    private String ean;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private ProductBrand brand;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "measure_type")
    @Enumerated(EnumType.STRING)
    private ProductMeasure measureType;

    @Column(nullable = false, name = "is_active")
    private boolean isActive;

    @Column(nullable = false)
    private Long totalStock;

    @PrePersist
    public void setActive() {
        this.isActive = true;
        this.sku = generateSKU();
        this.totalStock = 0L;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_sector", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private List<Sector> sectors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    private String generateSKU() {
        String part1 = this.name.substring(0, 3);
        String part2 = this.brand.getName().substring(0, 3);
        String part3 = this.measureType.toString().substring(0, 3);
        StringBuilder stringBuilder = new StringBuilder("");

        int letterA = 65;
        int letterZ = 95;
        for (int i = 0; i < 10; i++) {
            int letterCode = (int) (Math.random() * (letterZ - letterA) + letterA);
            stringBuilder.append(Character.toChars(letterCode));
        }
        return part1 + "-" + part2 + "-" + part3 + "-" + stringBuilder.toString();
    }
}
