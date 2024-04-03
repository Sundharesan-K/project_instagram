package com.app.instagram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();
        http.authorizeRequests()
            // Specify URL patterns to match against and allow access to authenticated users
            .antMatchers("/admin/**")
            .permitAll()
            .antMatchers("/**")
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
