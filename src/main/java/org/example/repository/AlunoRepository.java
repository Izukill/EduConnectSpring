package org.example.repository;

import org.example.model.Aluno;
import org.example.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {


    Optional<Aluno> findByMatricula(String matricula);

    List<Aluno> findByTurma(Turma turma);

    Optional<Aluno> findByNomeAndEmail(String nome, String email);

    long countByTurmaNome(String nomeTurma);

    @Query("SELECT a FROM Aluno a LEFT JOIN FETCH a.mensalidades WHERE a.id = :id")
    Optional<Aluno> findByIdWithMensalidades(@Param("id") Long id);


    @Query("SELECT a FROM Aluno a WHERE " +
            "(:nome IS NULL OR LOWER(a.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:matricula IS NULL OR a.matricula = :matricula)")
    Page<Aluno> buscarPor(@Param("nome") String nome,
                          @Param("matricula") String matricula,
                          org.springframework.data.domain.Pageable pageable);
}