package edu.marcio.stock.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lots")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(nullable = false, name = "expiry_date")
    private LocalDate expiryDate;

    @Column(nullable = false, name = "received_at")
    private LocalDateTime receivedAt;

    @PrePersist
    public void fillReceivedDate() {
        this.receivedAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
