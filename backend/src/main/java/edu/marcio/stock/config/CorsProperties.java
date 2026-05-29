package edu.marcio.stock.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "app.cors")
@Valid
public class CorsProperties {
    @NotEmpty(message = "provide at least one origin")
    private List<String> allowedOrigins;

    @NotEmpty
    private List<String> allowedMethods;

    @NotEmpty
    private List<String> allowedHeaders;

    private boolean allowedCredentials;
}
