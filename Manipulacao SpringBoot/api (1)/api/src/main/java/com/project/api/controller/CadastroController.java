package com.project.api.controller;

import com.project.api.domain.usuario.DadosCadastro;
import com.project.api.domain.usuario.Usuario;
import com.project.api.domain.usuario.Usuariorepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private Usuariorepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastro dados){
        var usuario = new Usuario(dados);
        repository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
