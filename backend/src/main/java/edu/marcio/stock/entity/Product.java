package edu.marcio.stock.entity;

import java.math.BigDecimal;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @Column(nullable = false)
    private String ean;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private ProductBrand brand;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "measure_type")
    private ProductMeasure measureType;

    @Column(nullable = false, name = "is_active")
    private boolean isActive;

    @PrePersist
    public void setActive() {
        this.isActive = true;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_sector", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private List<Sector> sectors = new ArrayList<>();

    private String generateSKU() {
        String part1 = this.name.substring(0, 2);
        String part2 = this.brand.getName().substring(0, 2);
        String part3 = this.measureType.toString().substring(0, 1);
        StringBuffer stringBuffer = new StringBuffer("");

        for (int i = 65; i < 91; i++) {
            stringBuffer.append(Character.toChars(i));
        }
        return part1 + "-" + part2 + "-" + part3 + "-" + stringBuffer.toString();
    }
}
