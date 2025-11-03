package tech.portifolio.urlshortener.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.portifolio.urlshortener.controller.dto.ShortenUrlRequest;
import tech.portifolio.urlshortener.controller.dto.ShortenUrlResponse;
import tech.portifolio.urlshortener.entities.UrlEntity;
import tech.portifolio.urlshortener.repository.UrlRepository;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request,
                                                         HttpServletRequest servletRequest) {

        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(new ShortenUrlRequest());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {

        var url = urlRepository.findById(id);

        if(url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url.get().getFullUrl()))
                .build();
    }
}
