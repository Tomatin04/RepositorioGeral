package com.project.api.medico;

import com.project.api.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidades especialidade, DadosEndereco endereco) {
}
