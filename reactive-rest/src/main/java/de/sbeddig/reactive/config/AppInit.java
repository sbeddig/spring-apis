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
                        Flux.just(aArticle("1", "camera"),
                                aArticle("2", "pc"),
                                aArticle("3", "tablet"),
                                aArticle("4", "notebook"),
                                aArticle("5", "smartphone"))
                                .flatMap(addArticle::add)
                )
                .thenMany(mongoDbClient.findAll())
                .subscribe(article -> LoggerFactory.getLogger(AppInit.class).info("{} saved.", article));
    }

    private Article aArticle(String id, String name) {
        return Article.builder()
                .id(id)
                .name(name)
                .build();
    }

}
