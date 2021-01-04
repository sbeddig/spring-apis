package de.sbeddig.reactive.config;

import de.sbeddig.reactive.adapter.repository.ArticleMongoRepository;
import de.sbeddig.reactive.adapter.repository.MongoDbClient;
import de.sbeddig.reactive.domain.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public ArticleRepository articleRepository(MongoDbClient articleMongoRepository) {
        return new ArticleMongoRepository(articleMongoRepository);
    }

}
