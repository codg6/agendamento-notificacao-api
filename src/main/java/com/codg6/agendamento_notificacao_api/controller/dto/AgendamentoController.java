package com.codg6.agendamento_notificacao_api.controller.dto;

import com.codg6.agendamento_notificacao_api.business.AgendamentoService;
import com.codg6.agendamento_notificacao_api.controller.dto.in.AgendamentoRecord;
import com.codg6.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService service;

    @PostMapping
    public ResponseEntity<AgendamentoRecordOut> gravarAgendamentos(@RequestBody AgendamentoRecord agendamento) {
        return ResponseEntity.ok(service.gravarAgendamento(agendamento));
    }
}
