package org.example.repository;

import org.example.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {


    boolean existsByNome(String nome);

    Optional<Turma> findByNome(String nome);


    @Query("SELECT t FROM Turma t WHERE " +
            "(:nome IS NULL OR LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:turno IS NULL OR LOWER(t.turno) LIKE LOWER(CONCAT('%', :turno, '%')))")
    Page<Turma> buscarPor(@Param("nome") String nome,
                          @Param("turno") String turno,
                          Pageable pageable);
}