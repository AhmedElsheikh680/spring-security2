package com.spring.security.config;

import com.spring.security.jwt.JWTTokenFilter;
import com.spring.security.jwt.JWTValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests((requests)->requests.anyRequest().permitAll());
//        http.formLogin();
//        http.httpBasic();

//        http.authorizeRequests((requests)->requests.anyRequest().denyAll()).formLogin().and().httpBasic();

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration corsConfiguration = new CorsConfiguration();
                        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                        corsConfiguration.setAllowCredentials(true);
                        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
                        corsConfiguration.setMaxAge(2500L);
                        return corsConfiguration;
                    }
                }).and()
//                .csrf().disable()
//                .csrf().ignoringAntMatchers("/other/*").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .addFilterAfter(new JWTTokenFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTValidationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/about/*").permitAll()
                .antMatchers("/connect/*").permitAll()
                .antMatchers("/other/*").permitAll()
                .antMatchers("/football/*").hasRole("USER")
                .antMatchers("/basketball/*").hasRole("ADMIN")
                .antMatchers("/swimming/*").hasRole("MANAGER")
                .antMatchers("/subscribers/*").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/login").authenticated()
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
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
