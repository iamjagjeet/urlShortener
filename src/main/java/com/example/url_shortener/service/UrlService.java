package com.example.url_shortener.service;

import com.example.url_shortener.dto.CreateUrlRequest;
import com.example.url_shortener.dto.UpdateUrlRequest;
import com.example.url_shortener.entity.UrlMapping;
import com.example.url_shortener.exception.ResourceNotFoundException;
import com.example.url_shortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public UrlMapping createShortUrl(CreateUrlRequest request) {
        String shortCode = generateUniqueShortCode();

        UrlMapping mapping = UrlMapping.builder()
                .url(request.getUrl())
                .shortCode(shortCode)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .accessCount(0L)
                .build();

        return urlRepository.save(mapping);
    }

    public UrlMapping getByShortCode(String shortCode) {
        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Short URL not found"));

        mapping.setAccessCount(mapping.getAccessCount() + 1);
        return urlRepository.save(mapping);
    }

    public UrlMapping updateShortUrl(String shortCode, UpdateUrlRequest request) {
        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Short URL not found"));

        mapping.setUrl(request.getUrl());
        mapping.setUpdatedAt(Instant.now());

        return urlRepository.save(mapping);
    }

    public void deleteShortUrl(String shortCode) {
        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Short URL not found"));

        urlRepository.delete(mapping);
    }

    public UrlMapping getStats(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Short URL not found"));
    }

    private String generateUniqueShortCode() {
        String code;
        do {
            code = generateRandomCode(6);
        } while (urlRepository.existsByShortCode(code));
        return code;
    }

    private String generateRandomCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(BASE62.charAt(RANDOM.nextInt(BASE62.length())));
        }
        return sb.toString();
    }
}
