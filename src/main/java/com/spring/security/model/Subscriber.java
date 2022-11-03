package com.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {

    private Long id;

    private String email; //username in spring security

    private String password;

    private String role; //authorities in spring security
}
