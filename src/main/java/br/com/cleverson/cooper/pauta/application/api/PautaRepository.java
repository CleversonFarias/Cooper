package br.com.cleverson.cooper.pauta.application.api;

import br.com.cleverson.cooper.pauta.domain.Pauta;

import java.util.UUID;

public interface PautaRepository {
    Pauta salva(Pauta pauta);
    Pauta buscaPautaPorId(UUID idPauta);
}
