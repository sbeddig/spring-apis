package de.sbeddig.graphql.config;

import de.sbeddig.graphql.domain.ArticleRepository;
import de.sbeddig.graphql.usecase.AddArticle;
import de.sbeddig.graphql.usecase.DeleteArticle;
import de.sbeddig.graphql.usecase.FindArticle;
import de.sbeddig.graphql.usecase.UpdateArticle;
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
