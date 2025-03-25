package com.project.api.domain.medico;

public record DadosListagemMedicos(Long id, String nome, String email, String crm, Especialidades especialidade) {

    public DadosListagemMedicos(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
