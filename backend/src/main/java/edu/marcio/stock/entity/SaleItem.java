package edu.marcio.stock.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "sale_items")
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // Kept for reporting/analytics

    @Column(nullable = false, name = "quantity_sold")
    @Positive(message = "the quantity must be bigger than zero")
    private Long quantitySold; // Stored in units or grams

    @Column(nullable = false, name = "sold_at_price")
    private BigDecimal soldAtPrice; // THE SNAPSHOT PRICE

    @Column(nullable = false, name = "applied_discount")
    private BigDecimal appliedDiscount;
}
