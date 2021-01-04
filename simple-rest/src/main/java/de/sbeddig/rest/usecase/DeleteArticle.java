package de.sbeddig.rest.usecase;

import de.sbeddig.rest.domain.Article;
import de.sbeddig.rest.domain.ArticleRepository;

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
