package com.st1.platform.cs.dto.request;

import com.st1.platform.cs.dto.ArticleCommentDto;
import com.st1.platform.all.dto.UserInfoDto;

public record ArticleCommentRequest(
        Long articleId,
        Long parentCommentId,
        String content
) {

    public static ArticleCommentRequest of(Long articleId, String content) {
        return ArticleCommentRequest.of(articleId, null, content);
    }

    public static ArticleCommentRequest of(Long articleId, Long parentCommentId, String content) {
        return new ArticleCommentRequest(articleId, parentCommentId, content);
    }

    public ArticleCommentDto toDto(UserInfoDto userInfoDto) {
        return ArticleCommentDto.of(
                articleId,
                userInfoDto,
                parentCommentId,
                content
        );
    }

}
