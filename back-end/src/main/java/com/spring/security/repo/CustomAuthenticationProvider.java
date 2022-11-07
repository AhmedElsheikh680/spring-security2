package com.spring.security.repo;

import com.spring.security.model.Authority;
import com.spring.security.model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private SubscriberRepo subscriberRepo;

    private PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public CustomAuthenticationProvider(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Subscriber> subscribers = subscriberRepo.findByEmail(username);
        if (subscribers.isEmpty()){
            throw new BadCredentialsException("Invalid User You Must Be Register");
        } else {
            if(passwordEncoder.matches(password, subscribers.get(0).getPassword())){
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority(subscribers.get(0).getAuthorities()));
                return new UsernamePasswordAuthenticationToken(username, password, getAuthority(subscribers.get(0).getAuthorities()));
            } else{
                throw new BadCredentialsException("Invalid Password!!");
            }
        }
    }

    List<SimpleGrantedAuthority> getAuthority(List<Authority> authorities){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (Authority authority: authorities){
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authorities.get(0).getName()));
        }
        return simpleGrantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
