package de.sbeddig.reactive.usecase;

import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.domain.ArticleRepository;
import reactor.core.publisher.Mono;

public class DeleteArticle {

    private final ArticleRepository articleRepository;

    public DeleteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<Article> delete(String id) {
        return articleRepository.getById(id)
                .flatMap(article -> articleRepository.deleteById(article.getId()).thenReturn(article));
    }


}
