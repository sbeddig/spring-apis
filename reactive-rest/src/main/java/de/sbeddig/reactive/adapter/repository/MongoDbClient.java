package de.sbeddig.reactive.adapter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDbClient extends ReactiveMongoRepository<ArticleDocument, String> {
}
