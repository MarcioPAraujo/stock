package edu.marcio.stock.entity;

import java.time.LocalDateTime;

import edu.marcio.stock.enums.SaleStatus;
import edu.marcio.stock.enums.SaleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "sale_type")
    private SaleType saleType;

    @Column(nullable = false)
    private SaleStatus status;

    @Column(nullable = false, name = "total_price")
    @Positive(message = "The value should be greater than zero")
    private Double totalPrice;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void presistCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

}
