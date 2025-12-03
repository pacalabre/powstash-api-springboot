package com.powstash.PowStash.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name="states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
    private Set<Mountain> mountains= new HashSet<Mountain>();

}
