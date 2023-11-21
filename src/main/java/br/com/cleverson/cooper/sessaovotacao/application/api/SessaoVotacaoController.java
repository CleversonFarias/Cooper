package br.com.cleverson.cooper.sessaovotacao.application.api;

import br.com.cleverson.cooper.sessaovotacao.application.service.SessaoVotacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoController implements SessaoVotacaoAPI {
    private final SessaoVotacaoService sessaoVotacaoService;

    @Override
    public SessaoAberturaResponse abreSessao(SessaoAberturaRequest sessaoAberturaRequest) {
        log.info("´[inicio] SessaoVotacaoController - abreSessao");
        SessaoAberturaResponse sessaoAberturaResponse = sessaoVotacaoService.abreSessao(sessaoAberturaRequest);
        log.info("´[finaliza] SessaoVotacaoController - abreSessao");
        return sessaoAberturaResponse;
    }

    @Override
    public VotoResponse recebeVoto(UUID idSessao, VotoRequest novoVoto) {
        log.debug("´[inicio] SessaoVotacaoController - recebeVoto");
        log.debug("´[idSessao] {}", idSessao);
        VotoResponse votoResponse = sessaoVotacaoService.recebeVoto(idSessao, novoVoto);
        log.debug("´[finaliza] SessaoVotacaoController - recebeVoto");
        return votoResponse;
    }

    @Override
    public ResultadoSessaoResponse obtemResultado(UUID idSessao) {
        log.info("´[inicio] SessaoVotacaoController - obtemResultado");
        log.info("´[idSessao] {}", idSessao);
        ResultadoSessaoResponse resultado = sessaoVotacaoService.obtemResultado(idSessao);
        log.info("´[finaliza] SessaoVotacaoController - obtemResultado");
        return resultado;
    }

}
