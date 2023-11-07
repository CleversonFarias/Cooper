package br.com.cleverson.cooper.sessaovotacao.application.service;

import br.com.cleverson.cooper.sessaovotacao.application.api.SessaoAberturaRequest;
import br.com.cleverson.cooper.sessaovotacao.application.api.SessaoAberturaResponse;

public interface SessaoVotacaoService {
    SessaoAberturaResponse abreSessao(SessaoAberturaRequest sessaoAberturaRequest);
}
