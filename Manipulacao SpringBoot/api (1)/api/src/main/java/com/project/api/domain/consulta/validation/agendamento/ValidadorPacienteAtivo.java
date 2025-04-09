package com.project.api.domain.consulta.validation.agendamento;

import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.domain.paciente.PacienteRepository;
import com.project.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private PacienteRepository repository;

    public  void validar(DadosAgendamentoConsulta dados){

        var pacienteAtivo = repository.findAtivoById(dados.idPaciente());
        if(pacienteAtivo == 0){
            throw  new ValidacaoException("O paciente não está mais ativo para poder marcar uma consulta");
        }
    }

}
