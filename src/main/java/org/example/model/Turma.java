package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.net.DatagramSocket;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "turmas")
public class Turma {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private UUID lookupId;

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
    }

    @OneToMany(mappedBy = "turma", cascade = CascadeType.PERSIST)
    private List<Aluno> alunos;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE)
    private List<Aula> aulas;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "turma_disciplina", joinColumns = @JoinColumn(name = "fk_idTurma"),
            inverseJoinColumns = @JoinColumn(name = "fk_idDisciplina"))
    private List<Disciplina> disciplinas;


    private String turno;
    private String nome;



}
