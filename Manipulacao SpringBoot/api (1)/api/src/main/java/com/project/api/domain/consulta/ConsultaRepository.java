package com.project.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(@NotNull Long aLong, LocalDateTime horaInicial, LocalDateTime horaFinal);
}
