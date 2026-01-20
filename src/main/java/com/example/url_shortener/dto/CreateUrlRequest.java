package com.example.url_shortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUrlRequest {

    @NotBlank
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "URL must start with http or https"
    )
    @Schema(description = "Original long URL to shorten", example = "https://example.com/long/url", required = true)
    private String url;
}
