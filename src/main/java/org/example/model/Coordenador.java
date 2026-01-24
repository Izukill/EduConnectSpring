package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Coordenadores")
public class Coordenador extends Pessoa {


    private float salario;


}


