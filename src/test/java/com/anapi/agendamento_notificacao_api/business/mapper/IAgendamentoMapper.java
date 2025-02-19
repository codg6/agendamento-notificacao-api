package com.anapi.agendamento_notificacao_api.business.mapper;

import com.anapi.agendamento_notificacao_api.controller.dto.in.AgendamentoRecord;
import com.anapi.agendamento_notificacao_api.controller.dto.ou.AgendamentoRecordOut;
import com.anapi.agendamento_notificacao_api.infrastructure.entities.Agendamento;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IAgendamentoMapper {

    Agendamento paraEntity(AgendamentoRecord agendamento);

    AgendamentoRecordOut paraOut(Agendamento agendamento);
}
