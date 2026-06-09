package edu.marcio.stock.dto.supplier;

import java.util.List;

import edu.marcio.stock.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SupplierRequest {
    @NotBlank(message = "The company name must be provided")
    private String corporateName;

    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}-\\d{2}$", message = "Invalid format")
    @NotBlank(message = "The cnpj should be informed")
    private String cnpj;

    private List<Product> productIds;
}
