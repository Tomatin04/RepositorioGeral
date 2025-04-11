package com.project.api.controller;

import com.project.api.domain.consulta.AgendaDeConsultas;
import com.project.api.domain.consulta.DadosAgendamentoConsulta;
import com.project.api.domain.consulta.DadosDetalahadosConsulta;
import com.project.api.domain.medico.Especialidades;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureJsonTesters

class ConsultasControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJacksonTesterJSON;

    @Autowired
    private JacksonTester<DadosDetalahadosConsulta> dadosDetalahadosConsultaJacksonTesterJSON;

    @MockBean
    private AgendaDeConsultas agenda;

    @Test
    @DisplayName("Codigo 400 quando as info invalidas")
    @WithMockUser
    void agendarC01() throws Exception {
        var response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Codigo 200 quando as info validas")
    @WithMockUser
    void agendarC02() throws Exception {
        var data =  LocalDateTime.now().plusHours(1);
        var especialidade = Especialidades.CARDIOLOGIA;

        var dadosDetalha = new DadosDetalahadosConsulta(null, 1l, 1l, data);
        when(agenda.agendar(any())).thenReturn(dadosDetalha);

        var response = mvc.perform(
                post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJacksonTesterJSON.write(
                                new DadosAgendamentoConsulta(1l,1l, data, especialidade)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonResposta = dadosDetalahadosConsultaJacksonTesterJSON.write(dadosDetalha).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonResposta);
    }
}