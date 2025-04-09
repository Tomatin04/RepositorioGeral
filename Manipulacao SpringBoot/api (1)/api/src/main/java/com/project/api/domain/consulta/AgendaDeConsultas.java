package com.project.api.domain.consulta;

import com.project.api.domain.consulta.validation.agendamento.ValidadorAgendamentoDeConsultas;
import com.project.api.domain.medico.MedicoRepository;
import com.project.api.domain.paciente.PacienteRepository;
import com.project.api.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.api.domain.medico.Medico;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    public DadosDetalahadosConsulta agendar (DadosAgendamentoConsulta dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw  new ValidacaoException("ID do paciente não encontrado");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw  new ValidacaoException("ID do medico não existe");
        }

        validadores.forEach(v -> v.validar(dados));


        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        if(medico == null){
            throw new ValidacaoException("Não existe medico disponivel nesta data");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data());


        consultaRepository.save(consulta);

        return new DadosDetalahadosConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialização é necessario se não for espesificaod o medico");
        }

        return medicoRepository.escolhaAleatoriaDoMedico(dados.especialidade(), dados.data());
    }
}
