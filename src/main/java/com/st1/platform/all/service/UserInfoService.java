package com.st1.platform.all.service;

import com.st1.platform.all.dto.UserInfoDto;
import com.st1.platform.all.domain.UserInfo;
import com.st1.platform.all.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Transactional(readOnly = true)
    public Optional<UserInfoDto> searchUser(String username) {
        return userInfoRepository.findById(username)
                .map(UserInfoDto::from);
    }

    public UserInfoDto saveUser(String username, String password, String email, String nickname, String team) {
        return UserInfoDto.from(
                userInfoRepository.save(UserInfo.of(username, password, email, nickname, team, username))
        );
    }

}
