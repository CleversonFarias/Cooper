package br.com.cleverson.cooper.sessaovotacao.application.service;

import br.com.cleverson.cooper.pauta.application.api.PautaService;
import br.com.cleverson.cooper.pauta.domain.Pauta;
import br.com.cleverson.cooper.sessaovotacao.application.api.*;
import br.com.cleverson.cooper.sessaovotacao.domain.PublicadorResultadoSessao;
import br.com.cleverson.cooper.sessaovotacao.domain.SessaoVotacao;
import br.com.cleverson.cooper.sessaovotacao.domain.VotoPauta;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoApplicationService implements SessaoVotacaoService {
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;
    private final AssociadoService associadoService;
    private final PublicadorResultadoSessao publicadorResultadoSessao;

    @Override
    public SessaoAberturaResponse abreSessao(SessaoAberturaRequest sessaoAberturaRequest) {
        log.info("´[inicio] SessaoVotacaoApplicationService - abreSessao");
        Pauta pauta = pautaService.getPautaPorId(sessaoAberturaRequest.getIdPauta());
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.salva(new SessaoVotacao(sessaoAberturaRequest,pauta));
        log.info("´[finaliza] SessaoVotacaoApplicationService - abreSessao");
        return new SessaoAberturaResponse(sessaoVotacao);
    }

    @Override
    public VotoResponse recebeVoto(UUID idSessao, VotoRequest novoVoto) {
        log.debug("´[inicio] SessaoVotacaoApplicationService - recebeVoto");
        SessaoVotacao sessao = sessaoVotacaoRepository.buscaPorId(idSessao);
        VotoPauta voto = sessao.recebeVoto(novoVoto,associadoService, publicadorResultadoSessao);
        sessaoVotacaoRepository.salva(sessao);
        log.debug("´[finaliza] SessaoVotacaoApplicationService - recebeVoto");
        return new VotoResponse(voto);
    }

    @Override
    public ResultadoSessaoResponse obtemResultado(UUID idSessao) {
        log.info("´[inicio] SessaoVotacaoApplicationService - obtemResultado");
        SessaoVotacao sessao = sessaoVotacaoRepository.buscaPorId(idSessao);
        ResultadoSessaoResponse resultado = sessao.resultadoSessao(publicadorResultadoSessao);
        sessaoVotacaoRepository.salva(sessao);
        log.info("´[finaliza] SessaoVotacaoApplicationService - obtemResultado");
        return resultado;
    }
}
