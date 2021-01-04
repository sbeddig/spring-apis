package de.sbeddig.reactive.usecase;

import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.domain.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class AddArticle {

    private final ArticleRepository articleRepository;
    private final Logger logger = LoggerFactory.getLogger(AddArticle.class);

    public AddArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Mono<Article> add(Article article) {
        return articleRepository.add(article);
    }

}
