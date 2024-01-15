package br.com.cleverson.cooper.sessaovotacao.domain;

import br.com.cleverson.cooper.sessaovotacao.application.api.ResultadoSessaoResponse;

public interface PublicadorResultadoSessao {
    void publica(ResultadoSessaoResponse resultadoSessaoResponse);
}
