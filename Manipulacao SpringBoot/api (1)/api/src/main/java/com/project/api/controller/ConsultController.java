package com.project.api.controller;


import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.domain.consulta.DadosDetalahadosConsulta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta")
public class ConsultController {

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@Valid @RequestBody DadosAgendamentoConsulta dados){
        return ResponseEntity.ok(new DadosDetalahadosConsulta(null, null, null,null));
    }
}
