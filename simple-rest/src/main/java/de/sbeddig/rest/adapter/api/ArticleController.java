package de.sbeddig.rest.adapter.api;

import de.sbeddig.rest.domain.Article;
import de.sbeddig.rest.usecase.AddArticle;
import de.sbeddig.rest.usecase.DeleteArticle;
import de.sbeddig.rest.usecase.FindArticle;
import de.sbeddig.rest.usecase.UpdateArticle;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "rest/articles")
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


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticleApiModel>> getAllArticles() {
        return ResponseEntity.ok(findArticleUseCase.getAll().stream().map(ArticleApiModel::of).collect(Collectors.toList()));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleApiModel> getArticleById(@PathVariable String id) {
        Optional<Article> article = findArticleUseCase.getById(id);
        return article.map(value -> ResponseEntity.ok(ArticleApiModel.of(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addArticle(@RequestBody ArticleApiModel articleApiModel) {
        Article savedArticle = addArticleUseCase.add(toArticle(articleApiModel));
        UriComponents uriComponents = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedArticle.getId());

        return ResponseEntity.created(uriComponents.toUri()).build();
    }


    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateArticle(@PathVariable String id, @RequestBody ArticleApiModel articleApiModel) {
        Optional<Article> article = updateArticleUseCase.update(id, toArticle(articleApiModel));

        if (article.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ArticleApiModel> deleteArticle(@PathVariable String id) {
        Optional<Article> deletedArticle = deleteArticleUseCase.delete(id);
        return deletedArticle.map(article -> ResponseEntity.ok(ArticleApiModel.of(article))).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private Article toArticle(ArticleApiModel articleApiModel) {
        return Article.builder()
                .id(articleApiModel.getId())
                .name(articleApiModel.getName())
                .build();
    }

}
