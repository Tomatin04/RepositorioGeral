package com.project.api.controller;


import com.project.api.domain.consulta.AgendaDeConsultas;
import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.domain.consulta.DadosDetalahadosConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@Valid @RequestBody DadosAgendamentoConsulta dados){
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }
}
