package edu.marcio.stock.dto.sector;

import lombok.Data;

@Data
public class SectorProductsListingParamsRequest {
    private String productName;
    private String sku;
    private String ean;
    private String brand;
    private Boolean isActive;
}
