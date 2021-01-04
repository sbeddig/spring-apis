package de.sbeddig.graphql.adapter.graphql;

import de.sbeddig.graphql.domain.Article;
import de.sbeddig.graphql.usecase.FindArticle;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private final FindArticle findArticleUseCase;

    public Query(FindArticle findArticleUseCase) {
        this.findArticleUseCase = findArticleUseCase;
    }

    public List<Article> getAllArticles() {
        return findArticleUseCase.getAll();
    }

    public Optional<Article> getArticleById(String id) {
        return findArticleUseCase.getById(id);
    }

}
