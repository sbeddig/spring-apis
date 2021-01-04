package de.sbeddig.reactive.usecase;

import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.domain.ArticleRepository;
import reactor.core.publisher.Mono;

public class DeleteArticle {

    private final ArticleRepository articleRepository;

    public DeleteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<Void> delete(String id) {
        Mono<Article> articleFromRepo = articleRepository.getById(id);
        return articleFromRepo.then(articleRepository.deleteById(id)).switchIfEmpty(Mono.empty());
    }


}
