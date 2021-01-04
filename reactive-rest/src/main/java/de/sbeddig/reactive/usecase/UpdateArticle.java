package de.sbeddig.reactive.usecase;

import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.domain.ArticleRepository;
import reactor.core.publisher.Mono;

public class UpdateArticle {

    private final ArticleRepository articleRepository;

    public UpdateArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<Article> update(String id, Article article) {
        Mono<Article> articleFromDb = articleRepository.getById(id);

        return articleFromDb.flatMap(
                old -> {
                    article.setId(id);
                    return articleRepository.add(article);
                }
        ).switchIfEmpty(Mono.empty());
    }

}
