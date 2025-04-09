package com.project.api.domain.consulta.validation.agendamento;

import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {

    public void validar(DadosAgendamentoConsulta dados){
        var daatConsulta = dados.data();

        var domingo =  daatConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var anstesDaAberturadaClinica = daatConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = daatConsulta.getHour() > 18;

        if(domingo || anstesDaAberturadaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta agendada fora de horário");
        }
    }
}
