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
@Table(name = "Professores")
public class Professor extends Pessoa {


    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Aula> aulas;

    private float salario;


}
