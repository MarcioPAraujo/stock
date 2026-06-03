package edu.marcio.stock.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "The name of the prioduct is mandatory")
    private String name;

    @NotBlank(message = "The EAN code should be informed")
    private String ean;

    @NotBlank(message = "The brand should be informed")
    private String brandId;

    @NotBlank(message = "The price should be informed")
    @Positive(message = "The price should be bigger than zero")
    private BigDecimal price;

    @NotBlank(message = "The measure type should be informed")
    @Pattern(regexp = "UNIT|MILLIGRAMS", message = "The measure type should be UNIT or MILLIGRAMS")
    private String measureType;
}
