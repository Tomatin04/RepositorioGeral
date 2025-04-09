package com.project.api.domain.consulta.validation.agendamento;

import com.project.api.domain.consulta.ConsultaRepository;
import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaUnica implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var consultaUnica = repository.existsByMedicoIdAndData( dados.idMedico(),  dados.data());
        if(consultaUnica){
            throw new ValidacaoException("O medico já tem uma consulta agendada neste horario");
        }
    }
}
