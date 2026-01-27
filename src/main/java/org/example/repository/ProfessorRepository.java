package org.example.repository;

import org.example.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {


    List<Professor> findByNomeOrEmail(String nome, String email);


    List<Professor> findBySalarioGreaterThanEqual(Float salario);


    @Query("SELECT p FROM Professor p WHERE " +
            "(:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:email IS NULL OR LOWER(p.email) = LOWER(:email)) AND " +
            "(:salarioMinimo IS NULL OR p.salario >= :salarioMinimo)")
    Page<Professor> buscarPor(@Param("nome") String nome,
                              @Param("email") String email,
                              @Param("salarioMinimo") Float salarioMinimo,
                              Pageable pageable);
}