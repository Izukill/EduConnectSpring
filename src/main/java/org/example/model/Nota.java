package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Notas")
public class Nota {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private UUID lookupId;

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
    }


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_idAluno", nullable = false)
    private Aluno aluno;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_idSimulado", nullable = false)
    private Simulado simulado;


    private Double notaLinguagens;
    private Double notaRedacao;
    private Double notaCienciasHumanas;
    private Double notaCienciasNatureza;
    private Double notaMatematica;


}
