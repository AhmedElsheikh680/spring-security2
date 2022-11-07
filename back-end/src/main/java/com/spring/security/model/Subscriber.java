package com.spring.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email; //username in spring security

    private String password;

//    private String role; //authorities in spring security

    @JsonIgnore
    @OneToMany(mappedBy = "subscriber", fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
