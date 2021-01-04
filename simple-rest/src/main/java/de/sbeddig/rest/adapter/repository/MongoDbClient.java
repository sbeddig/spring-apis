package de.sbeddig.rest.adapter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoDbClient extends MongoRepository<ArticleDocument, String> {
}
