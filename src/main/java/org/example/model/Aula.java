package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "aulas")
public class Aula {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID lookupId;

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
    }


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_idProfessor")
    private Professor professor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_idTurma")
    private Turma turma;

    @OneToMany(mappedBy = "aula", cascade = CascadeType.REMOVE)
    private List<Presenca> presencas;

    private String conteudo;
    private LocalDate data;
    private String observacoes;




}
