package de.sbeddig.reactive.usecase;

import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.domain.ArticleRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FindArticle {

    private final ArticleRepository articleRepository;

    public FindArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Flux<Article> getAll() {
        return articleRepository.getAll();
    }

    public Mono<Article> getById(String id) {
        return articleRepository.getById(id);
    }
}
