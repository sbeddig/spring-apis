package de.sbeddig.graphql.adapter.repository;

import de.sbeddig.graphql.domain.Article;
import de.sbeddig.graphql.domain.ArticleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArticleMongoRepository implements ArticleRepository {

    private final MongoDbClient mongoClient;

    public ArticleMongoRepository(MongoDbClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Article add(Article article) {
        return toArticle(mongoClient.save(ArticleDocument.of(article)));
    }

    @Override
    public List<Article> getAll() {
        return mongoClient.findAll().stream().map(this::toArticle).collect(Collectors.toList());
    }

    @Override
    public Optional<Article> getById(String id) {
        Optional<ArticleDocument> document = mongoClient.findById(id);
        return document.map(this::toArticle);
    }

    @Override
    public Optional<Article> deleteById(String id) {
        Optional<ArticleDocument> document = mongoClient.findById(id);
        if (document.isPresent()) {
            mongoClient.deleteById(id);
            return document.map(this::toArticle);
        }
        return Optional.empty();
    }

    private Article toArticle(ArticleDocument articleDocument) {
        return Article.builder()
                .id(articleDocument.getId())
                .name(articleDocument.getName())
                .build();
    }

}
