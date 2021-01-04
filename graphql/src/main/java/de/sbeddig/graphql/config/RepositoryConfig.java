package de.sbeddig.graphql.config;

import de.sbeddig.graphql.adapter.repository.ArticleMongoRepository;
import de.sbeddig.graphql.adapter.repository.MongoDbClient;
import de.sbeddig.graphql.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ArticleRepository articleRepository(MongoDbClient articleMongoRepository) {
        return new ArticleMongoRepository(articleMongoRepository);
    }

}
