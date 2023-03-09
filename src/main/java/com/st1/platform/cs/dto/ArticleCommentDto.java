package com.st1.platform.cs.dto;

import com.st1.platform.cs.domain.Article;
import com.st1.platform.cs.domain.ArticleComment;
import com.st1.platform.all.domain.UserInfo;
import com.st1.platform.all.dto.UserInfoDto;

import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        UserInfoDto userInfoDto,
        Long parentCommentId,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleCommentDto of(Long articleId, UserInfoDto userInfoDto, String content) {
        return ArticleCommentDto.of(articleId, userInfoDto, null, content);
    }

    public static ArticleCommentDto of(Long articleId, UserInfoDto userInfoDto, Long parentCommentId, String content) {
        return ArticleCommentDto.of(null, articleId, userInfoDto, parentCommentId, content, null, null, null, null);
    }

    public static ArticleCommentDto of(Long id, Long articleId, UserInfoDto userInfoDto, Long parentCommentId, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleCommentDto(id, articleId, userInfoDto, parentCommentId, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleCommentDto from(ArticleComment entity) {
        return new ArticleCommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                UserInfoDto.from(entity.getUserInfo()),
                entity.getParentCommentId(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public ArticleComment toEntity(Article article, UserInfo userInfo) {
        return ArticleComment.of(
                article,
                userInfo,
                content
        );
    }

}
