package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "disciplinas")

public class Disciplina {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID lookupId;

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
    }


    @ManyToMany(mappedBy = "disciplinas")
    private List<Turma> turmas;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_idProfessor", nullable = true)
    private Professor professor;

    private Integer ch;
    private String ementa;
    private String nome;



}
