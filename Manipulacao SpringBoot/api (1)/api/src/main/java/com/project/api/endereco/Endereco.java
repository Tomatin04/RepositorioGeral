package com.project.api.endereco;

//import jakarta.persistence.*;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter

@AllArgsConstructor
@Embeddable
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.bairro = dados.bairro();
        this.complemento = dados.complemento();
    }

    public Endereco() {
        this.logradouro = null;
        this.cidade = null;
        this.uf = null;
        this.cep = null;
        this.numero = null;
        this.bairro = null;
        this.complemento = null;
    }
}
