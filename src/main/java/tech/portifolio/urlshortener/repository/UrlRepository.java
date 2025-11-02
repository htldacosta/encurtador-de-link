package tech.portifolio.urlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.portifolio.urlshortener.entities.UrlEntity;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, String> {
}
