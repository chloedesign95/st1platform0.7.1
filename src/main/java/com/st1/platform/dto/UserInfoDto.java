package com.st1.platform.dto;

import com.st1.platform.domain.UserInfo;

import java.time.LocalDateTime;

public record UserInfoDto(
        String userId,
        String userPw,
        String email,
        String nickname,
        String team,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static UserInfoDto of(String userId, String userPw, String email, String nickname, String team) {
        return new UserInfoDto(userId, userPw, email, nickname, team, null, null, null, null);
    }

    public static UserInfoDto of(String userId, String userPw, String email, String nickname, String team, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserInfoDto(userId, userPw, email, nickname, team, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserInfoDto from(UserInfo entity) {
        return new UserInfoDto(
                entity.getUserId(),
                entity.getUserPw(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getTeam(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserInfo toEntity() {
        return UserInfo.of(
                userId,
                userPw,
                email,
                nickname,
                team
        );
    }

}
