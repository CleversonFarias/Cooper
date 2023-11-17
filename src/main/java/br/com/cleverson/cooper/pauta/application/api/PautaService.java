package br.com.cleverson.cooper.pauta.application.api;

import br.com.cleverson.cooper.pauta.domain.Pauta;

import java.util.UUID;

public interface PautaService {
    PautaCadastradaResponse cadastraPauta(NovaPautaRequest novaPauta);
    Pauta getPautaPorId(UUID idPauta);
}
