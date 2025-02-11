package com.anapi.agendamento_notificacao_api.business;

import com.anapi.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.anapi.agendamento_notificacao_api.controller.dto.in.AgendamentoRecord;
import com.anapi.agendamento_notificacao_api.controller.dto.ou.AgendamentoRecordOut;
import com.anapi.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.anapi.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    private final IAgendamentoMapper mapper;

    public AgendamentoRecordOut gravarAgendamento(AgendamentoRecord agendamento) {
        return mapper.paraOut(repository.save(mapper.paraEntity(agendamento)));
    }
}
