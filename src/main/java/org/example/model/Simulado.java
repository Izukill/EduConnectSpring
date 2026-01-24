package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Simulado")
public class Simulado {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID lookupId;

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
    }


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "simulado_turma", joinColumns = @JoinColumn(name = "fk_idSimulado"),
            inverseJoinColumns = @JoinColumn(name = "fk_idTurma"))
    private List<Turma> turma;


    @OneToMany(mappedBy = "simulado", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Nota> notas;

    private String descricao;

    @Column(name = "qtd_questoes")
    private Integer qtdQuestoes;



}