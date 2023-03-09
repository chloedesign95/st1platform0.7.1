package com.st1.platform.all.repository;

import com.st1.platform.all.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

//13.12 Repository 생성
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
