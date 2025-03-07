package com.codg6.agendamento_notificacao_api.business;

import com.codg6.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.codg6.agendamento_notificacao_api.controller.dto.in.AgendamentoRecord;
import com.codg6.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut;
import com.codg6.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.codg6.agendamento_notificacao_api.infrastructure.enums.StatusNotificacaoEnum;
import com.codg6.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService service;

    @Mock
    private AgendamentoRepository repository;

    @Mock
    private IAgendamentoMapper mapper;

    private Agendamento agendamentoEntity;
    private AgendamentoRecord agendamentoRecord;
    private AgendamentoRecordOut agendamentoRecordOut;

    @BeforeEach
    void setUp() {

        agendamentoEntity = new Agendamento(
                1L,
                "email@email.com",
                "556188888888",
                LocalDateTime.of(2025, 1,2, 11, 11, 11),
                LocalDateTime.now(),
                null,
                "Favor vim buscar o produto",
                StatusNotificacaoEnum.AGENDADO);

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
    void deveGravarAgendamentoComSucesso() throws Exception {
        when(mapper.paraEntity(agendamentoRecord)).thenReturn(agendamentoEntity);
        when(repository.save(agendamentoEntity)).thenReturn(agendamentoEntity);
        when(mapper.paraOut(agendamentoEntity)).thenReturn(agendamentoRecordOut);

        AgendamentoRecordOut out = service.gravarAgendamento(agendamentoRecord);

        verify(mapper, times(1)).paraEntity(agendamentoRecord);
        verify(repository, times(1)).save(agendamentoEntity);
        verify(mapper, times(1)).paraOut(agendamentoEntity);

        assertThat(out).usingRecursiveComparison().isEqualTo(agendamentoRecordOut);
    }
}
