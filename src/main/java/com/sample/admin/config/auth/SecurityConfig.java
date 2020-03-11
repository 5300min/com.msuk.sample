package com.sample.admin.config.auth;

import com.sample.admin.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity /* SpringSecurity 설정 활성화 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() /* h2-console 사용하기 위해 해당 옵션들을 disable하겠다. */
                .and()
                    .authorizeRequests() /* url별 권한 관리 옵션설정 이것을 설정해야 아래 antMatchers사용 가능 */
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "h2-console/**").permitAll() /* 권한 관리대상 지정옵션, url, http 메소드별 관리 가능*/
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) /* 특정 url은 특정 권한을 가진 사람만 가능하게 했음. */
                    .anyRequest().authenticated()  /* 설정되지 않은 나머지 요청(anyRequest)에 대해서는 모두 인증된 사용자들에게만 허용(authenticated) */
                .and()
                    .logout()
                        .logoutSuccessUrl("/") /* 로그아웃시 설정 진입점, 로그아웃 성공시 루트로 이동 */
                .and()
                    .oauth2Login() /* oauth2 로그인에 대한 설정 진입점 */
                        .userInfoEndpoint() /* oauth2 로그인 성공 이후 사용자 정보를 가져올때 설정 */
                            .userService(customOAuth2UserService); /* 로그인 성공시 후속조치를 진행할 userservice interface의 구현체 등록 / 유저정보 가져온 상태에서 추가 작업도 명시 가능함 */
    }
}
