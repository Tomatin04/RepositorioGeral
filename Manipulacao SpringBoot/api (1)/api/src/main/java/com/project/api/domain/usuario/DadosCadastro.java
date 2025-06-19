package com.project.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastro(
        @NotBlank
        String login,
        @NotBlank
        String senha) {
}
