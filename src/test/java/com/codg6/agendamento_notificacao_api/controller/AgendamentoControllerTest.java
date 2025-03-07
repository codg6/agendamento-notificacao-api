package com.codg6.agendamento_notificacao_api.controller;

import com.codg6.agendamento_notificacao_api.business.AgendamentoService;
import com.codg6.agendamento_notificacao_api.controller.dto.in.AgendamentoRecord;
import com.codg6.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut;
import com.codg6.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AgendamentoControllerTest {

    @InjectMocks
    AgendamentoController agendamentoController;

    @Mock
    AgendamentoService service;

    private AgendamentoRecord agendamentoRecord;
    private AgendamentoRecordOut agendamentoRecordOut;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(agendamentoController).build();

        objectMapper.registerModule(new JavaTimeModule());

        agendamentoRecord = new AgendamentoRecord(
                "email@email.com",
                "556199999999",
                "Favor retornar a loja com urgência",
                LocalDateTime.of(2025, 1,2, 11, 11, 11));

        agendamentoRecordOut = new AgendamentoRecordOut(
                1L,
                "email@email.com",
                "556188888888",
                "Favor vim buscar o produto",
                LocalDateTime.of(2025, 1,2, 11, 11, 11),
                StatusNotificacaoEnum.AGENDADO);
    }

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {

        when(service.gravarAgendamento(agendamentoRecord)).thenReturn(agendamentoRecordOut);

        mockMvc.perform(post("/agendamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(agendamentoRecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(agendamentoRecordOut.id()))
                .andExpect(jsonPath("$.emailDestinatario").value(agendamentoRecordOut.emailDestinatario()))
                .andExpect(jsonPath("$.telefoneDestinatario").value(agendamentoRecordOut.telefoneDestinatario()))
                .andExpect(jsonPath("$.mensagem").value(agendamentoRecordOut.mensagem()))
                .andExpect(jsonPath("$.dataHoraEnvio").value("02-01-2025 11:11:11"))
                .andExpect(jsonPath("$.statusNotificacao").value("AGENDADO"));

        verify(service, times(1)).gravarAgendamento(agendamentoRecord);
    }
}
