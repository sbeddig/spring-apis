package de.sbeddig.rest.domain;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    Article add(Article article);
    List<Article> getAll();
    Optional<Article> getById(String id);
    Optional<Article> deleteById(String id);

}
