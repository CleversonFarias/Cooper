package br.com.cleverson.cooper.sessaovotacao.infra;

import br.com.cleverson.cooper.sessaovotacao.application.service.SessaoVotacaoRepository;
import br.com.cleverson.cooper.sessaovotacao.domain.SessaoVotacao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

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
}
