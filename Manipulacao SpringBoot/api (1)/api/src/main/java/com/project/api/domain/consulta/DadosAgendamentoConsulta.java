package com.project.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.project.api.domain.medico.Especialidades;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        @JsonAlias("medico_id")Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidades especialidade
) {

}
