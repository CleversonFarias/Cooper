package br.com.cleverson.cooper.sessaovotacao.infra;

import br.com.cleverson.cooper.sessaovotacao.application.service.SessaoVotacaoRepository;
import br.com.cleverson.cooper.sessaovotacao.domain.SessaoVotacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoInfraRepository implements SessaoVotacaoRepository {
    private final SessaoVotacaoSpringDataJPARepository sessaoVotacaoSpringDataJPARepository;

    @Override
    public SessaoVotacao salva(SessaoVotacao sessaoVotacao) {
        log.info("´[inicio] SessaoVotacaoApplicationService - abreSessao");
        sessaoVotacaoSpringDataJPARepository.save(sessaoVotacao);
        log.info("´[finaliza] SessaoVotacaoApplicationService - abreSessao");
        return sessaoVotacao;
    }

    @Override
    public SessaoVotacao buscaPorId(UUID idSessao) {
        log.info("´[inicio] SessaoVotacaoApplicationService - buscaoPorId");
        SessaoVotacao sessao = sessaoVotacaoSpringDataJPARepository.findById(idSessao)
                .orElseThrow(() -> new RuntimeException("Sessao não encontrada"));
        log.info("´[finaliza] SessaoVotacaoApplicationService - buscaoPorId");
        return sessao;
    }
}
