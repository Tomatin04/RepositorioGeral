package com.project.api.domain.consulta.validation.agendamento;

import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecedencia implements ValidadorAgendamentoDeConsultas {

    public  void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();

        var diferenca = Duration.between(agora, dataConsulta).toMinutes();

        if(diferenca < 30){
            throw new ValidacaoException("A consulta deve ser agendada com mais de 30 min de antecedencia");
        }
    }
}
