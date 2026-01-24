package org.example.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Alunos")
public class Aluno extends Pessoa{


    @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Nota> notas;


    @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE, orphanRemoval = true) //o padrão do one2many já é lazy
    private List<Mensalidade> mensalidades;


    @OneToMany(mappedBy = "aluno", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Presenca> presencas;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "turma_id", nullable = true)
    private Turma turma;


    @Column(unique = true, nullable = false)
    private String matricula;


}
