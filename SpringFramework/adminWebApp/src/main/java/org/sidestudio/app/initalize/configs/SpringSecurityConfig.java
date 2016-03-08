package org.sidestudio.app.initalize.configs;

import org.sidestudio.app.components.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 설정
 *
 * @author logan
 * @since 2016-03-08
 */

@Configuration
@EnableWebSecurity // 웹 보안 활성
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/resources/**"); // css, js 등등 리소스 정보 Security Url 패턴에 제외 설정
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests() // 요청별 인증 권한 설정
                .antMatchers("/").permitAll() // permitAll : 무조건 접근 허용
                .antMatchers("/books", "/book").hasRole("ADMIN") // /users, /user url 접속시 권한이 ADMIN이 있는 유저만 가능 하도록 설정
                .anyRequest().authenticated() // 위에 설정한 url 제외 모든 경로에 기본인증이 필요 하도록 설정
                .and()
            .formLogin()
                .loginPage("/login") // Login 페이지 URL
                .usernameParameter("id") // username Parameter 명 설정
                .passwordParameter("pw") // password Parameter 명 설정
                .loginProcessingUrl("/loginProcess") // 로그인시 호출할 URL
                .defaultSuccessUrl("/loginSuccess", true) // defaultSuccessUrl(로그인 성공시, Referer가 없을때 호출할 URL, 로그인시 성공시 무조건 지정한 URL로 이동 여부 기본값[false])
//                .failureUrl("/") // 로그인 실패시 호출할 URL 기본 /login?error
                .permitAll() // 로그인에 필요한 모든 URL 무조건 접근을 허용
                .and()
            .logout()
                .logoutUrl("/logout") // 로그 아웃 URL 설정
                .logoutSuccessUrl("/") // 로그아웃 성공시 호출 URL
                .deleteCookies("JSESSIONID") // 쿠키 삭제 ',' 사용 하여 복수개를 지정할수 있다
                .invalidateHttpSession(true) // 세션 제거 여부
                .and()
            .exceptionHandling() // 권한이 없는 URL 호출시 처리
                .accessDeniedPage("/") // 403 페이지 호출 URL
                .and()
            .sessionManagement()
                .invalidSessionUrl("/login")
                .and()
            .csrf().disable(); //CSRF 비활성화
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 로그인 처리 Provider 커스텀
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

