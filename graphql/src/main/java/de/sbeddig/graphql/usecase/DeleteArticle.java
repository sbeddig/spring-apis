package de.sbeddig.graphql.usecase;

import de.sbeddig.graphql.domain.Article;
import de.sbeddig.graphql.domain.ArticleRepository;

import java.util.Optional;

public class DeleteArticle {

    private final ArticleRepository articleRepository;

    public DeleteArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Optional<Article> delete(String id) {
        return articleRepository.deleteById(id);
    }


}
