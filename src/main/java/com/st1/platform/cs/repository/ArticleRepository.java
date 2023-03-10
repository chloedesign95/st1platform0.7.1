package com.st1.platform.cs.repository;

import com.st1.platform.cs.domain.Article;
import com.st1.platform.cs.domain.QArticle;
import com.st1.platform.cs.repository.querydsl.ArticleRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//8.3 : SpringBoot RestAPI.
@RepositoryRestResource
//5.14 : ArticleRepository Interface 생성 및 작성.
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        /*Todo : 나중에 추가됨.*/ArticleRepositoryCustom,
        /*Todo : 나중에 추가됨.*/QuerydslPredicateExecutor<Article>,
        /*Todo : 나중에 추가됨.*/ QuerydslBinderCustomizer<QArticle> {

    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserInfo_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserInfo_NicknameContaining(String nickname, Pageable pageable);

    void deleteByIdAndUserInfo_UserId(Long articleId, String userid);

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtags, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtags.any().hashtagName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

}
