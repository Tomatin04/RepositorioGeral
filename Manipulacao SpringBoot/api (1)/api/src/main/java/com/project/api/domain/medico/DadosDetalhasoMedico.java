package com.project.api.domain.medico;

import com.project.api.domain.endereco.Endereco;

public record DadosDetalhasoMedico(Long id, String nome, String email, String crm, String telefone, Especialidades especialidade, Endereco endereco) {

    public DadosDetalhasoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
