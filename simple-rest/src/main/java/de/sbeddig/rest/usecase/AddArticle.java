package de.sbeddig.rest.usecase;

import de.sbeddig.rest.domain.Article;
import de.sbeddig.rest.domain.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddArticle {

    private final ArticleRepository articleRepository;
    private final Logger logger = LoggerFactory.getLogger(AddArticle.class);

    public AddArticle(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article add(Article article) {
        Article savedArticle = articleRepository.add(article);
        logger.info("{} saved.", savedArticle);
        return savedArticle;
    }

}
