package com.app.instagram.config;

import static com.app.instagram.constant.APIEndPoint.API_ADMIN;
import static com.app.instagram.constant.APIEndPoint.API_LIKE;
import static com.app.instagram.constant.APIEndPoint.API_POST;
import static com.app.instagram.constant.APIEndPoint.API_USER;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String STRING = "/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        http.authorizeRequests()
            // Specify URL patterns to match against and allow access to authenticated users
            .antMatchers(API_ADMIN+STRING, API_USER+STRING, API_POST+STRING, API_LIKE+STRING)
            .permitAll()
            .antMatchers(STRING)
            .authenticated()
            .and()
            // Add other security configurations as needed, for example:
            .csrf()
            .disable() // Disable CSRF protection if not needed
            .formLogin()
            .disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
