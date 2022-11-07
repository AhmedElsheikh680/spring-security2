package com.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;


    private String name;

    @ManyToOne
    @JoinColumn(name = "sub_id")
    private Subscriber subscriber;
}
