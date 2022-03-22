package com.project.gymmembership.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("admin").password("admin").roles("ADMIN"))
                .withUser(users.username("member").password("member").roles("MEMBER"))
                .withUser(users.username("instructor").password("instructor").roles("INSTRUCTOR"));


    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//            .and()
//            .formLogin()
//                .loginPage("/showLoginForm")
//                .loginProcessingUrl("/authenticateTheUser")
//                .permitAll()
//            .and()
//            .logout()
//                .permitAll();
//
//    }
}
