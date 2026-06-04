package edu.marcio.stock.dto.product;

import java.math.BigDecimal;

import edu.marcio.stock.entity.Product;
import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.enums.ProductMeasure;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponse {

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.sku = product.getSku();
        this.ean = product.getEan();
        this.name = product.getName();
        this.isActive = product.isActive();
        this.measureType = product.getMeasureType();
    }

    private String id;

    private String sku;

    private String ean;

    private String name;

    private ProductBrand brand;

    private BigDecimal price;

    private ProductMeasure measureType;

    private boolean isActive;
}
