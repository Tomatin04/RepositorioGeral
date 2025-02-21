package com.project.api.medico;

import com.project.api.endereco.Endereco;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nome;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidades especialidade;

    @Embedded
    private Endereco endereco;


    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();

        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
