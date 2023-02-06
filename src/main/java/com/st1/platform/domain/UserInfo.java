package com.st1.platform.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(name = "userinfo", indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserInfo extends AuditingFields {
    @Id @Column(length = 50, name = "UserID") private String userId; //아이디
    @Setter @Column(nullable = false, name = "UserPW") private String userPw; //비밀번호
    @Setter @Column(length = 100, name = "Email") private String email; //이메일
    @Setter @Column(length = 100,  name = "UserNAME") private String nickname; //이름
    @Setter @Column(name = "Team") private String team; //소속


    protected UserInfo() {}

    private UserInfo(String userId, String userPw, String email, String nickname, String team, String createdBy) {
        this.userId = userId;
        this.userPw = userPw;
        this.email = email;
        this.nickname = nickname;
        this.team = team;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }

    public static UserInfo of(String userId, String userPw, String email, String nickname, String team) {
        return UserInfo.of(userId, userPw, email, nickname, team, null);
    }

    public static UserInfo of(String userId, String userPw, String email, String nickname, String team, String createdBy) {
        return new UserInfo(userId, userPw, email, nickname, team, createdBy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo that)) return false;
        return this.getUserId() != null && this.getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }

}
