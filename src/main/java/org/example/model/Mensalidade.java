package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Mensalidades")
public class Mensalidade {



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

    private LocalDate data;
    private LocalDate vencimento;
    private Double valor;


    @Enumerated(EnumType.STRING)
    private StatusMensalidade status;


}
