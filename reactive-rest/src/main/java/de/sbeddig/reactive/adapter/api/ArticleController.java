package de.sbeddig.reactive.adapter.api;

import de.sbeddig.reactive.domain.Article;
import de.sbeddig.reactive.usecase.AddArticle;
import de.sbeddig.reactive.usecase.DeleteArticle;
import de.sbeddig.reactive.usecase.FindArticle;
import de.sbeddig.reactive.usecase.UpdateArticle;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping(path = "reactive/articles")
public class ArticleController {

    private final AddArticle addArticleUseCase;
    private final FindArticle findArticleUseCase;
    private final DeleteArticle deleteArticleUseCase;
    private final UpdateArticle updateArticleUseCase;

    public ArticleController(AddArticle addArticleUseCase, FindArticle getArticleUseCase, DeleteArticle deleteArticleUseCase, UpdateArticle updateArticleUseCase) {
        this.addArticleUseCase = addArticleUseCase;
        this.findArticleUseCase = getArticleUseCase;
        this.deleteArticleUseCase = deleteArticleUseCase;
        this.updateArticleUseCase = updateArticleUseCase;
    }

    @GetMapping
    public Flux<ArticleApiModel> getAllArticles() {
        return findArticleUseCase.getAll().flatMap(article -> Mono.just(ArticleApiModel.of(article)));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ArticleApiModel>> getArticleById(@PathVariable String id) {
        return findArticleUseCase.getById(id)
                .map(article -> ResponseEntity.ok(ArticleApiModel.of(article)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Void>> addArticle(@RequestBody ArticleApiModel articleApiModel, ServerHttpRequest request) {
        return addArticleUseCase.add(toArticle(articleApiModel))
                .map(article -> {
                    UriComponents uriComponents = UriComponentsBuilder.fromHttpRequest(request).path("/{id}").buildAndExpand(article.getId());
                    return ResponseEntity.created(uriComponents.toUri()).build();
                });
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ArticleApiModel>> updateArticle(@PathVariable String id,
                                                               @RequestBody ArticleApiModel articleApiModel) {
        return updateArticleUseCase.update(id, toArticle(articleApiModel))
                .map(article -> ResponseEntity.ok(ArticleApiModel.of(article)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> deleteArticle(@PathVariable String id) {
        return deleteArticleUseCase.delete(id)
                .map(article -> ResponseEntity.noContent().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(aLong -> aLong+".Event");
    }

    private Article toArticle(ArticleApiModel articleApiModel) {
        return Article.builder()
                .id(articleApiModel.getId())
                .name(articleApiModel.getName())
                .build();
    }

}
