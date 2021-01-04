package de.sbeddig.graphql.usecase;

import de.sbeddig.graphql.domain.Article;
import de.sbeddig.graphql.domain.ArticleRepository;

import java.util.List;
import java.util.Optional;

public class FindArticle {

    private final ArticleRepository articleRepository;

    public FindArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAll() {
        return articleRepository.getAll();
    }

    public Optional<Article> getById(String id) {
        return articleRepository.getById(id);
    }
}
