package tech.portifolio.urlshortener.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class UrlEntity {

    @Id
    private String id;

    private String fullUrl;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiresAT;

    public UrlEntity() {

    }

    public UrlEntity(String id, String fullUrl, LocalDateTime experiesAT) {
        this.id = id;
        this.fullUrl = fullUrl;
        this.expiresAT = experiesAT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getExpiresAT() {
        return expiresAT;
    }

    public void setExpiresAT(LocalDateTime expiresAT) {
        this.expiresAT = expiresAT;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }
}
