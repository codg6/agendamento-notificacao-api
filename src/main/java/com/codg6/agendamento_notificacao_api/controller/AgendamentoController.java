package com.codg6.agendamento_notificacao_api.controller;

import com.codg6.agendamento_notificacao_api.business.AgendamentoService;
import com.codg6.agendamento_notificacao_api.controller.dto.in.AgendamentoRecord;
import com.codg6.agendamento_notificacao_api.controller.dto.out.AgendamentoRecordOut;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService service;

    @PostMapping
    public ResponseEntity<AgendamentoRecordOut> gravarAgendamentos(@RequestBody AgendamentoRecord agendamento) {
        return ResponseEntity.ok(service.gravarAgendamento(agendamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoRecordOut> buscarAgendamentoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.buscarAgendamentoPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AgendamentoRecordOut> cancelarAgendamentoPorId(@PathVariable("id") Long id) {
        service.cancelarAgendamento(id);
        return ResponseEntity.accepted().build();
    }
}
