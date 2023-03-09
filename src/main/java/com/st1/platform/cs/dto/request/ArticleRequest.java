package com.st1.platform.cs.dto.request;

import com.st1.platform.cs.dto.ArticleDto;
import com.st1.platform.all.dto.HashtagDto;
import com.st1.platform.all.dto.UserInfoDto;

import java.util.Set;

public record ArticleRequest(
        String title,
        String content
) {

    public static ArticleRequest of(String title, String content) {
        return new ArticleRequest(title, content);
    }

    public ArticleDto toDto(UserInfoDto userInfoDto) {
        return toDto(userInfoDto, null);
    }

    public ArticleDto toDto(UserInfoDto userInfoDto, Set<HashtagDto> hashtagDtos) {
        return ArticleDto.of(
                userInfoDto,
                title,
                content,
                hashtagDtos
        );
    }

}
