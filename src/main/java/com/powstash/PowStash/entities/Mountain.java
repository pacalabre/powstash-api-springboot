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

    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name="pass_id")
    private Pass pass;
}
