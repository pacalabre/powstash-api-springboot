package com.powstash.PowStash.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name="mountains")
@AllArgsConstructor
@NoArgsConstructor
public class Mountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="address")
    private String address;

    @Column(name="longitude")
    private String longitude;

    @Column(name="latitude")
    private String latitude ;

    @Column(name="localKnowledge")
    private String localKnowledge;

    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name="pass_id")
    private Pass pass;
}
