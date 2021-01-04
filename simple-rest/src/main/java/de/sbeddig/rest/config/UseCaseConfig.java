package de.sbeddig.rest.config;

import de.sbeddig.rest.domain.ArticleRepository;
import de.sbeddig.rest.usecase.AddArticle;
import de.sbeddig.rest.usecase.DeleteArticle;
import de.sbeddig.rest.usecase.FindArticle;
import de.sbeddig.rest.usecase.UpdateArticle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AddArticle addArticle(ArticleRepository articleRepository) {
        return new AddArticle(articleRepository);
    }

    @Bean
    public DeleteArticle deleteArticle(ArticleRepository articleRepository) {
        return new DeleteArticle(articleRepository);
    }

    @Bean
    public FindArticle findArticle(ArticleRepository articleRepository) {
        return new FindArticle(articleRepository);
    }

    @Bean
    public UpdateArticle updateArticle(ArticleRepository articleRepository) {
        return new UpdateArticle(articleRepository);
    }

}
