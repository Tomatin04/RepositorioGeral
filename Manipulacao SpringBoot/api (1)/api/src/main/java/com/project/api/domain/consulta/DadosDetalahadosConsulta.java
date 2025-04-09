package com.project.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalahadosConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalahadosConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
