package de.sbeddig.rest.config;

import de.sbeddig.rest.adapter.repository.MongoDbClient;
import de.sbeddig.rest.domain.Article;
import de.sbeddig.rest.usecase.AddArticle;
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
        addArticle.add(createArticle("1", "camera"));
        addArticle.add(createArticle("2","pc"));
        addArticle.add(createArticle("3","tablet"));
        addArticle.add(createArticle("4","notebook"));
        addArticle.add(createArticle("5","smartphone"));
    }

    private Article createArticle(String id, String name) {
        return Article.builder()
                .id(id)
                .name(name)
                .build();
    }

}
