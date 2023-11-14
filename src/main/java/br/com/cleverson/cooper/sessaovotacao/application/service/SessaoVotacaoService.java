package br.com.cleverson.cooper.sessaovotacao.application.service;

import br.com.cleverson.cooper.sessaovotacao.application.api.SessaoAberturaRequest;
import br.com.cleverson.cooper.sessaovotacao.application.api.SessaoAberturaResponse;
import br.com.cleverson.cooper.sessaovotacao.application.api.VotoRequest;
import br.com.cleverson.cooper.sessaovotacao.application.api.VotoResponse;

import java.util.UUID;

public interface SessaoVotacaoService {
    SessaoAberturaResponse abreSessao(SessaoAberturaRequest sessaoAberturaRequest);
    VotoResponse recebeVoto(UUID idSessao, VotoRequest novoVoto);
}
