package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests((requests)->requests.anyRequest().permitAll());
//        http.formLogin();
//        http.httpBasic();

//        http.authorizeRequests((requests)->requests.anyRequest().denyAll()).formLogin().and().httpBasic();

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

    //I can Add users In Memory from AuthenticationManagerBuilder


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication().withUser("Ahmed").password("12345").authorities("admin")
////                .and().withUser("Mohamed").password("12345").authorities("player")
////                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
//
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//       UserDetails userDetails =  User.withUsername("Ahmed").password("12345").authorities("admin").build();
//      UserDetails userDetails2 =  User.withUsername("Mohamed").password("12345").authorities("player").build();
//      inMemoryUserDetailsManager.createUser(userDetails);
//      inMemoryUserDetailsManager.createUser(userDetails2);
//      auth.userDetailsService(inMemoryUserDetailsManager);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
