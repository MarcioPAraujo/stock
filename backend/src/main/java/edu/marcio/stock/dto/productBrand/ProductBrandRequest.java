package edu.marcio.stock.dto.productBrand;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductBrandRequest {
    @NotBlank(message = "The name of the product should be provided")
    @Length(max = 255, message = "The name should not have more than 255 characters")
    private String name;
}
