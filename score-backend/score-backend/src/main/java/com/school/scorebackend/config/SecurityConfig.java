package com.school.scorebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                // lambda标准开启跨域，不用调用and()
                .cors((CorsConfigurer<HttpSecurity> cors) -> {})
                // 关闭csrf
                .csrf(csrf -> csrf.disable())
                // 所有接口全部放行
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                // 关闭自带登录页面、http基础认证弹窗
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());
        return http.build();
    }
}