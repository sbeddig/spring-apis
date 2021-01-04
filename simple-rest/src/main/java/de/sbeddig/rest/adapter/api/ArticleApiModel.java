package de.sbeddig.rest.adapter.api;

import de.sbeddig.rest.domain.Article;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleApiModel {

    private String id;
    private String name;

    public static ArticleApiModel of(Article article) {
        return ArticleApiModel.builder()
                .id(article.getId())
                .name(article.getName())
                .build();
    }

}
