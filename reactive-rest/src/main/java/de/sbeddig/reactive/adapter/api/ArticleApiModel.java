package de.sbeddig.reactive.adapter.api;

import de.sbeddig.reactive.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
