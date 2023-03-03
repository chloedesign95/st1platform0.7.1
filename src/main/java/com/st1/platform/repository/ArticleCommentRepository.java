package com.st1.platform.repository;

import com.st1.platform.domain.ArticleComment;
import com.st1.platform.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//8.2 : : SpringBoot RestAPI
@RepositoryRestResource
//5.15 : ArticleCommentRepository Interface 생성 및 작성.
public interface ArticleCommentRepository extends
        /*Todo : 나중에 추가됨.*/JpaRepository<ArticleComment, Long>,
        /*Todo : 나중에 추가됨.*/QuerydslPredicateExecutor<ArticleComment>,
        /*Todo : 나중에 추가됨.*/QuerydslBinderCustomizer<QArticleComment> {

    List<ArticleComment> findByArticle_Id(Long articleId);
    void deleteByIdAndUserInfo_UserId(Long articleCommentId, String userId);

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

}
