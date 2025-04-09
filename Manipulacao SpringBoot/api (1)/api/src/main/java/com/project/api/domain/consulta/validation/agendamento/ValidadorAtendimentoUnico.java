package com.project.api.domain.consulta.validation.agendamento;

import com.project.api.domain.consulta.ConsultaRepository;
import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorAtendimentoUnico implements  ValidadorAgendamentoDeConsultas{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

        var horaInicial = dados.data().withHour(7);
        var horaFinal = dados.data().withHour(18);
        var pacienteOutraConsulta = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), horaInicial, horaFinal);
        if(pacienteOutraConsulta){
            throw new ValidacaoException("O Paciente já tem uma consulta marcada nas proximas 24h");
        }
    }

}


