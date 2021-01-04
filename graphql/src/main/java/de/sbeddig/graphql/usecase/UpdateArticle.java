package de.sbeddig.graphql.usecase;

import de.sbeddig.graphql.domain.Article;
import de.sbeddig.graphql.domain.ArticleRepository;

import java.util.Optional;

public class UpdateArticle {

    private final ArticleRepository articleRepository;

    public UpdateArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Optional<Article> update(String id, Article article) {
        Optional<Article> articleFromDb = articleRepository.getById(id);

        if (articleFromDb.isEmpty())
            return Optional.empty();

        article.setId(id);

        return Optional.of(articleRepository.add(article));
    }

}
