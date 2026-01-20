package com.example.url_shortener.controller;

import com.example.url_shortener.dto.CreateUrlRequest;
import com.example.url_shortener.dto.UpdateUrlRequest;
import com.example.url_shortener.entity.UrlMapping;
import com.example.url_shortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorten")
@RequiredArgsConstructor
@Tag(name = "URL Shortener", description = "Operations for shortening URLs")
public class UrlController {

    private final UrlService urlService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a short URL")
    public UrlMapping create(@Valid @RequestBody CreateUrlRequest request) {
        return urlService.createShortUrl(request);
    }

    @GetMapping("/{shortCode}")
    @Operation(summary = "Retrieve the original URL")
    public UrlMapping getOriginal(@PathVariable String shortCode) {
        return urlService.getByShortCode(shortCode);
    }

    @PutMapping("/{shortCode}")
    @Operation(summary = "Update an existing short URL")
    public UrlMapping update(
            @PathVariable String shortCode,
            @Valid @RequestBody UpdateUrlRequest request) {
        return urlService.updateShortUrl(shortCode, request);
    }

    @DeleteMapping("/{shortCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a short URL")
    public void delete(@PathVariable String shortCode) {
        urlService.deleteShortUrl(shortCode);
    }

    @GetMapping("/{shortCode}/stats")
    @Operation(summary = "Get short URL statistics")
    public UrlMapping stats(@PathVariable String shortCode) {
        return urlService.getStats(shortCode);
    }
}
