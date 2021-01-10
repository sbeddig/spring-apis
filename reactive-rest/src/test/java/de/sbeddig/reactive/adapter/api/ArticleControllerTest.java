package de.sbeddig.reactive.adapter.api;

import de.sbeddig.reactive.config.AppInit;
import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.domain.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class ArticleControllerTest {

    @Autowired
    private ArticleController articleController;

    @Autowired
    private AppInit appInit;

    private WebTestClient client;

    @BeforeEach
    public void init() {
        client = WebTestClient.bindToController(articleController).build();
        appInit.initRepository();
    }

    @Test
    void allArticlesShouldBeReturned() {
        client
                .get()
                .uri("/reactive/articles")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ArticleApiModel.class).hasSize(5);
    }

    @Test
    void articleShouldBeDeleted() {
        client.delete()
                .uri("/reactive/articles/1")
                .exchange()
                .expectStatus()
                .isNoContent();
        client.delete()
                .uri("/reactive/articles/1")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

}