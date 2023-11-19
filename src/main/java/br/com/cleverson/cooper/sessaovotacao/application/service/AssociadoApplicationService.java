package br.com.cleverson.cooper.sessaovotacao.application.service;

import br.com.cleverson.cooper.sessaovotacao.domain.SessaoVotacao;
import br.com.cleverson.cooper.sessaovotacao.domain.VotoPauta;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AssociadoApplicationService implements AssociadoService {
    @Override
    public void validaAssociadoAptoVoto(String cpfAssociado) {
        log.debug("[inicio] AssociadoApplicationService - validaAssociadoAptoVoto");

        log.debug("[finaliza] AssociadoApplicationService - validaAssociadoAptoVoto");
    }
}
