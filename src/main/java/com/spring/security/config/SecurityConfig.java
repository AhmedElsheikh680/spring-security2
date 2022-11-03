package com.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests((requests)->requests.anyRequest().permitAll());
//        http.formLogin();
//        http.httpBasic();

        http.authorizeRequests()
                .antMatchers("/about/*").permitAll()
                .antMatchers("/connect/*").permitAll()
                .antMatchers("/basketball/*").authenticated()
                .antMatchers("/football/*").authenticated()
                .antMatchers("/subscribers/*").authenticated()
                .antMatchers("/swimming/*").authenticated()
                .and().formLogin()
                .and().httpBasic();


    }
}
