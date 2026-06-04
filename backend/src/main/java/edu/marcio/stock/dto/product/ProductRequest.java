package edu.marcio.stock.dto.product;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import edu.marcio.stock.enums.ProductMeasure;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "The name of the prioduct is mandatory")
    private String name;

    @NotBlank(message = "The EAN code should be informed")
    @Length(max = 13, min = 13, message = "The EAN should have 13 numbers")
    private String ean;

    @NotBlank(message = "The brand should be informed")
    private String brandId;

    @NotBlank(message = "The price should be informed")
    @Positive(message = "The price should be bigger than zero")
    private BigDecimal price;

    @Pattern(regexp = "UNIT|MILLIGRAMS", message = "The measure type should be UNIT or MILLIGRAMS")
    private ProductMeasure measureType;

    @NotBlank(message = "The product should be related toa sector")
    private List<String> sectorsId;
}
