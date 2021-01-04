package de.sbeddig.rest.adapter.repository;

import de.sbeddig.rest.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDocument {

    @Id
    private String id;
    private String name;

    public static ArticleDocument of(Article article) {
        return ArticleDocument.builder()
                .id(article.getId())
                .name(article.getName())
                .build();
    }

}
