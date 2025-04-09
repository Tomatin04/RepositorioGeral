package com.project.api.domain.consulta.validation.agendamento;

import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.domain.medico.MedicoRepository;
import com.project.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() == null){
            return ;
        }

        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if(medicoAtivo == 0){
            throw new ValidacaoException("O medico requisitado não está ativo");
        }
    }
}
