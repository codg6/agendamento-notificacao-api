package com.codg6.agendamento_notificacao_api.business;

import com.codg6.agendamento_notificacao_api.business.mapper.IAgendamentoMapper;
import com.codg6.agendamento_notificacao_api.controller.dto.in.AgendamentoRecord;
import com.codg6.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut;
import com.codg6.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import com.codg6.agendamento_notificacao_api.infrastructure.exception.NotFoundException;
import com.codg6.agendamento_notificacao_api.infrastructure.repositories.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final IAgendamentoMapper mapper;

    public AgendamentoRecordOut gravarAgendamento(AgendamentoRecord agendamento){
        return mapper.paraOut(repository.save(mapper.paraEntity(agendamento)));
    }

    public AgendamentoRecordOut buscarAgendamentoPorId(Long id){
        return mapper.paraOut(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado")));
    }

    public void cancelarAgendamento(Long id){
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado"));

        repository.save(mapper.paraEntityCancelamento(agendamento));
    }
}
