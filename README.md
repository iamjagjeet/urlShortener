# URL Shortener API

A URL Shortening service built with **Spring Boot**, **JPA**, **H2**, and **Swagger**.  
This project demonstrates REST API development, database integration, and API documentation.

## Features
- Create short URLs
- Retrieve original URLs
- Update existing URLs
- Delete URLs
- Get URL access statistics
- Swagger UI for API documentation

## Tech Stack
- Java 17, Spring Boot
- H2 in-memory database (for development)
- Spring Data JPA
- Swagger/OpenAPI
- Maven


Endpoints

POST /shorten – Create short URL

GET /shorten/{shortCode} – Retrieve original URL

PUT /shorten/{shortCode} – Update URL

DELETE /shorten/{shortCode} – Delete URL

GET /shorten/{shortCode}/stats – Get stats

<img width="1470" height="956" alt="Image" src="https://github.com/user-attachments/assets/249866ba-60e1-4686-be52-b7abdcc0d69e" />

## Run Locally
```bash
mvn spring-boot:run
