package de.sbeddig.reactive.adapter.repository;

import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.domain.ArticleRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ArticleMongoRepository implements ArticleRepository {

    private final MongoDbClient mongoClient;

    public ArticleMongoRepository(MongoDbClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    private Article toArticle(ArticleDocument articleDocument) {
        return Article.builder()
                .id(articleDocument.getId())
                .name(articleDocument.getName())
                .build();
    }

    @Override
    public Mono<Article> add(Article article) {
        return mongoClient.save(ArticleDocument.of(article)).map(this::toArticle);
    }

    @Override
    public Flux<Article> getAll() {
        return mongoClient.findAll().map(this::toArticle);
    }

    @Override
    public Mono<Article> getById(String id) {
        return mongoClient.findById(id).map(this::toArticle);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoClient.deleteById(id);
    }
}
