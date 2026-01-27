package org.example.repository;

import org.example.model.Coordenador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {


    List<Coordenador> findByNomeOrEmail(String nome, String email);

    List<Coordenador> findBySalarioGreaterThanEqual(Float salario);

    @Query("SELECT c FROM Coordenador c WHERE " +
            "(:nome IS NULL OR LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:email IS NULL OR LOWER(c.email) = LOWER(:email)) AND " +
            "(:salarioMinimo IS NULL OR c.salario >= :salarioMinimo)")
    Page<Coordenador> buscarPor(@Param("nome") String nome,
                                @Param("email") String email,
                                @Param("salarioMinimo") Float salarioMinimo,
                                Pageable pageable);
}