package com.design.platform.resourceplatform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // API Design
        // ===============================================
        //
        // account/** 基础账号管理
        //      GET /     账号列表     用户权限
        //      GET /{id} 单个账号信息 用户权限
        //      POST /    更新账号     主人权限
        //      PUT /     新建账号     无权限
        //      DELETE /  注销账号     主人权限
        //
        // authority/** 权限管理暂无，统一最高管理员权限，用户权限动态验证
        //
        // category/** 资源种类管理
        //      GET /               种类列表       开放权限
        //      GET /{id}           种类信息       开放权限
        //      GET /{name}         种类下资源列表 开放权限
        //      POST /              更新种类信息   主人权限
        //      PUT /               新建资源种类   用户权限
        //      DELETE /            删除资源种类   主人权限
        //
        // file/** 文件管理
        //      GET /               文件列表     无权限
        //      GET /{id}           单个文件信息 开放权限
        //      GET /{id}/contained 文件包含于   开放权限
        //      GET /{id}/download  下载文件     开放权限
        //      POST /              更新文件     主人权限
        //      PUT /               上传新文件   用户权限
        //      DELETE /            删除文件     主人权限
        //
        // resource/** 资源管理
        //      GET /                 资源列表       无权限
        //      GET /{id}             单个资源信息   开放权限
        //      GET /{id}/favorite-by 资源被谁关注   开放权限
        //      GET /{id}/files       资源引用的文件 开放权限
        //      POST /                更新资源       主人权限
        //      PUT /                 创建资源       用户权限
        //      PATCH /favorite       收藏资源       用户权限
        //      DELETE /              删除资源       主人权限
        //
        // user/** 用户（非管理员）管理
        //      GET /               用户列表       无权限
        //      GET /{id}           单个用户信息   开放权限
        //      GET /{id}/followers 用户的关注者   开放权限
        //      GET /{id}/followed  用户关注的人   开放权限
        //      GET /{id}/published 用户发布的资源 开放权限
        //      GET /{id}/favorites 用户收藏的资源 开放权限
        //      GET /{id}/files     用户上传的文件 主人权限
        //      POST /              更新用户信息   主人权限
        //      PUT /               注册用户       开放权限
        //      PATCH /follow       关注用户       用户权限
        //      DELETE /            注销用户       主人权限

        http.authorizeRequests()

            .antMatchers("/api/login").authenticated()
            .antMatchers("/api/**").permitAll()

            .anyRequest().denyAll();
//
//            // account
//            .antMatchers(HttpMethod.GET,
//                         "/account/**").access("@securityConfiguration.denyUser()")
//            .antMatchers(HttpMethod.POST,
//                         "/account").access("@securityConfiguration.denyUser()")
//            .antMatchers(HttpMethod.PUT,
//                         "/account").access("@securityConfiguration.denyUser()")
//            .antMatchers(HttpMethod.POST,
//                         "/account").access("@securityConfiguration.denyUser()")
//
//
//            // category
//            .antMatchers(HttpMethod.GET,
//                         "/category/**").access("@securityConfiguration.denyUser()")
//            .antMatchers(HttpMethod.POST,
//                         "/category").access("@securityConfiguration.denyUser()")
//            .antMatchers(HttpMethod.PUT,
//                         "/category").access("@securityConfiguration.denyUser()")
//            .antMatchers(HttpMethod.DELETE,
//                         "/category").access("@securityConfiguration.denyUser()")
//
//            // file
//            .antMatchers(HttpMethod.GET,
//                         "/file/**").access("permitAll()")
//
//            // resource
//            .antMatchers(HttpMethod.GET,
//                         "/resource/**").access("@securityConfiguration.permitUser()")
//            .antMatchers(HttpMethod.GET,
//                         "/resource/*/files").access("@securityConfiguration.permitUser()")
//            .antMatchers(HttpMethod.GET,
//                         "/resource/*/favorites-by").access("@securityConfiguration.checkUser('MASTER')")
//            .antMatchers(HttpMethod.POST,
//                         "/resource").access("@securityConfiguration.checkUser('MASTER')")
//            .antMatchers(HttpMethod.PUT,
//                         "/resource").access("@securityConfiguration.permitUser()")
//            .antMatchers(HttpMethod.DELETE,
//                         "/resource").access("@securityConfiguration.checkUser('MASTER')")
//
//            // user
//            .antMatchers(HttpMethod.GET,
//                         "/user/**").access("@securityConfiguration.denyUser()")
//            .antMatchers(HttpMethod.GET,
//                         "/user/*/followed").access("@securityConfiguration.checkUser('MASTER')")
//            .antMatchers(HttpMethod.GET,
//                         "/user/*/followers").access("@securityConfiguration.checkUser('MASTER')")
//            .antMatchers(HttpMethod.GET,
//                         "/user/*/published").access("@securityConfiguration.checkUser('MASTER')")
//            .antMatchers(HttpMethod.GET,
//                         "/user/*/favorites").access("@securityConfiguration.checkUser('MASTER')")
//            .antMatchers(HttpMethod.GET,
//                         "/user/*/files").access("@securityConfiguration.checkUser('MASTER')")
//            .antMatchers(HttpMethod.POST,
//                         "/user").access("@securityConfiguration.checkUser('MASTER')")
//            .antMatchers(HttpMethod.PUT,
//                         "/user").access("permitAll()")
//            .antMatchers(HttpMethod.DELETE,
//                         "/user").access("@securityConfiguration.checkUser('MASTER')")


        http.httpBasic();
        http.cors().configurationSource(corsConfiguration());

        http.sessionManagement().disable();
        http.csrf().disable();
    }

    // Methods Access Control
    // ===============================================

    public boolean denyUser() {
        return true;
    }

    public boolean checkUser(String authority) {
        return true;
    }

    @Bean
    public boolean isAdmin() {
        return true;
    }

    @Bean
    public boolean isUser() {
        return true;
    }

    @Bean
    public boolean isMasterUser() {
        return true;
    }

    public boolean permitUser() {
        return true;
    }

    // Methods Beans
    // ===============================================

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
