package com.project.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable  paginacao);

    @Query(value = """
            SELECT m.* FROM Medicos m
            WHERE m.ativo = true
            AND m.especialidade = :especialidade
            AND m.id not in (
                SELECT c.medico_id FROM consultas c
                WHERE c.data = :data
            )
            ORDER BY RAND()
            limit 1
            """, nativeQuery = true)
    Medico escolhaAleatoriaDoMedico(Especialidades especialidade, @NotNull @Future LocalDateTime data);

    @Query(value = """
            SELECT m.ativo
            FROM Medicos m
            WHERE
            m.id = :id
            """, nativeQuery = true)
    Integer findAtivoById(Long id);
}
