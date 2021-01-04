package de.sbeddig.reactive.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleRepository {

    Mono<Article> add(Article article);
    Flux<Article> getAll();
    Mono<Article> getById(String id);
    Mono<Void> deleteById(String id);

}
