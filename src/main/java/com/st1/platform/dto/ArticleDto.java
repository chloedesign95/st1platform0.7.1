package com.st1.platform.dto;

import com.st1.platform.domain.Article;
import com.st1.platform.domain.UserInfo;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleDto(
        Long id,
        UserInfoDto userInfoDto,
        String title,
        String content,
        Set<HashtagDto> hashtagDtos,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleDto of(UserInfoDto userInfoDto, String title, String content, Set<HashtagDto> hashtagDtos) {
        return new ArticleDto(null, userInfoDto, title, content, hashtagDtos, null, null, null, null);
    }

    public static ArticleDto of(Long id, UserInfoDto userInfoDto, String title, String content, Set<HashtagDto> hashtagDtos, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleDto(id, userInfoDto, title, content, hashtagDtos, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                UserInfoDto.from(entity.getUserInfo()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtags().stream()
                        .map(HashtagDto::from)
                        .collect(Collectors.toUnmodifiableSet())
                ,
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Article toEntity(UserInfo userInfo) {
        return Article.of(
                userInfo,
                title,
                content
        );
    }

}
