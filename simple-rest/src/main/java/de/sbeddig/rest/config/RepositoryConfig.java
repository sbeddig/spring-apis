package de.sbeddig.rest.config;

import de.sbeddig.rest.adapter.repository.ArticleMongoRepository;
import de.sbeddig.rest.adapter.repository.MongoDbClient;
import de.sbeddig.rest.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ArticleRepository articleRepository(MongoDbClient articleMongoRepository) {
        return new ArticleMongoRepository(articleMongoRepository);
    }

}
