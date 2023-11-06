package br.com.cleverson.cooper.pauta.application.api;

import br.com.cleverson.cooper.pauta.domain.Pauta;

public interface PautaRepository {
    Pauta salva(Pauta pauta);
}
