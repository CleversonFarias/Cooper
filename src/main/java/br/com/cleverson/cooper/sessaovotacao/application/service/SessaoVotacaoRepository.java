package br.com.cleverson.cooper.sessaovotacao.application.service;

import br.com.cleverson.cooper.sessaovotacao.domain.SessaoVotacao;

import java.util.UUID;

public interface SessaoVotacaoRepository {
    SessaoVotacao salva(SessaoVotacao sessaoVotacao);
    SessaoVotacao buscaPorId(UUID idSessao);
}
