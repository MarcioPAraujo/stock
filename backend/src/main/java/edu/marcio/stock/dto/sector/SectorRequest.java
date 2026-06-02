package edu.marcio.stock.dto.sector;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SectorRequest {

    @NotBlank(message = "the sector name cannot be empty")
    private String name;
}
