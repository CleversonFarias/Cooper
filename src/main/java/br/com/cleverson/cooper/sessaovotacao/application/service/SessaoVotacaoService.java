package br.com.cleverson.cooper.sessaovotacao.application.service;

import br.com.cleverson.cooper.sessaovotacao.application.api.*;

import java.util.UUID;

public interface SessaoVotacaoService {
    SessaoAberturaResponse abreSessao(SessaoAberturaRequest sessaoAberturaRequest);
    VotoResponse recebeVoto(UUID idSessao, VotoRequest novoVoto);
    ResultadoSessaoResponse obtemResultado(UUID idSessao);
}
