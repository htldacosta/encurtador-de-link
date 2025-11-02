package tech.portifolio.urlshortener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.portifolio.urlshortener.controller.dto.ShortenUrlRequest;

@RestController
public class urlController {

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<Void> shortenUrl(@RequestBody ShortenUrlRequest request) {

        return ResponseEntity.ok().build();
    }
}
