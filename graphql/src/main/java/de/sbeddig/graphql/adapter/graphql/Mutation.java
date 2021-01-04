package de.sbeddig.graphql.adapter.graphql;

import de.sbeddig.graphql.domain.Article;
import de.sbeddig.graphql.usecase.AddArticle;
import de.sbeddig.graphql.usecase.DeleteArticle;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final AddArticle addArticleUseCase;
    private final DeleteArticle deleteArticleUseCase;

    public Mutation(AddArticle addArticleUseCase, DeleteArticle deleteArticleUseCase) {
        this.addArticleUseCase = addArticleUseCase;
        this.deleteArticleUseCase = deleteArticleUseCase;
    }

    public Article addArticle(String name) {
        return addArticleUseCase.add(Article.builder()
                .name(name)
                .build());
    }

    public Optional<Article> deleteArticle(String id) {
        return deleteArticleUseCase.delete(id);
    }

}
