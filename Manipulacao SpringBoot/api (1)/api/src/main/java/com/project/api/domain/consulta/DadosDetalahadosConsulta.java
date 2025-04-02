package com.project.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalahadosConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
}
