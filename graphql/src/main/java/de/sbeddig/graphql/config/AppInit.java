package de.sbeddig.graphql.config;

import de.sbeddig.graphql.adapter.repository.MongoDbClient;
import de.sbeddig.graphql.domain.Article;
import de.sbeddig.graphql.usecase.AddArticle;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {

    private final AddArticle addArticle;
    private final MongoDbClient mongoDbClient;

    public AppInit(AddArticle addArticle, MongoDbClient mongoDbClient) {
        this.addArticle = addArticle;
        this.mongoDbClient = mongoDbClient;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initRepository();
    }

    public void initRepository() {
        mongoDbClient.deleteAll();
        addArticle.add(createArticle("camera"));
        addArticle.add(createArticle("pc"));
        addArticle.add(createArticle("tablet"));
        addArticle.add(createArticle("notebook"));
        addArticle.add(createArticle("smartphone"));
    }

    private Article createArticle(String name) {
        return Article.builder()
                .name(name)
                .build();
    }

}
