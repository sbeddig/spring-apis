package de.sbeddig.reactive.config;

import de.sbeddig.reactive.adapter.repository.MongoDbClient;
import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.usecase.AddArticle;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

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
        mongoDbClient.deleteAll()
                .thenMany(
                        Flux.just("camera", "pc", "tablet", "notebook", "smartphone")
                                .map(this::createArticle)
                                .flatMap(addArticle::add)
                )
                .thenMany(mongoDbClient.findAll())
                .subscribe(article -> LoggerFactory.getLogger(AppInit.class).info("{} saved.", article));
    }

    private Article createArticle(String name) {
        return Article.builder()
                .name(name)
                .build();
    }

}
