package com.project.api.controller;

import com.project.api.medico.DadosCadastroMedico;
import com.project.api.medico.DadosListagemMedicos;
import com.project.api.medico.DadosAtualizacaoMedico;
import com.project.api.medico.Medico;
import com.project.api.medico.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    /*@GetMapping
    public List<DadosListagemMedicos> listar(){
        return repository.findAll().stream().map(DadosListagemMedicos::new).toList();
    }*/

    @GetMapping
    public Page<DadosListagemMedicos> listar(@PageableDefault(sort= {"nome"}, size = 10) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados ){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    /*
    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }*/

    @DeleteMapping
    @Transactional
    public void dletar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
