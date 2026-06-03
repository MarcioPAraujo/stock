package edu.marcio.stock.dto.product;

import java.math.BigDecimal;

import edu.marcio.stock.entity.ProductBrand;
import edu.marcio.stock.enums.ProductMeasure;
import lombok.Data;

@Data
public class ProductResponse {
    private String id;

    private String sku;

    private String ean;

    private String name;

    private ProductBrand brand;

    private BigDecimal price;

    private ProductMeasure measureType;

    private boolean isActive;
}
