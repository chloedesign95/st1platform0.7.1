package com.st1.platform.all.config;

import com.st1.platform.all.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

//4.13 : JPA Auditing 기능을 활성화 해준다.
//       JPA Auditing 기술 사용으로 Meta data 항목을 자동으로 셋팅되게끔 한다.
@EnableJpaAuditing
//4.12 : @Configuration으로 JpaConfig은 Configuration Bean 이 된다.
//       각종 설정을 잡을때 사용하게 된다.
@Configuration
//4.11 :  JpaConfig 클래스 생성 및 작성
public class JpaConfig {

    //4.14 : JPA Auditing 기능 중 CreatedBy ModifiedBy등 (글쓴이) 을 세팅
    @Bean //@Bean사람의 이름정보를 넣어주기위한 Config를 주기위한 Config를 @Bean어노테이션으로 잡아준다.
    public AuditorAware</*사람id형식*/String> auditorAware()/*메소드이름을 자동완성으로 쓴다.*/ {
        //4.15 : 임의데이터 넣기
        //return () -> Optional.of(value:"dldmswl");/*Todo: 이 부분은 나중에 임의데이터를 스프링시큐리티로 인증을 붙이면서 수정됨.*/
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(BoardPrincipal.class::cast)
                .map(BoardPrincipal::getUsername);
    }

}
