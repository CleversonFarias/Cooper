package br.com.cleverson.cooper.pauta.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class PautaApplicationService implements PautaService {
    @Override
    public PautaCadastradaResponse cadastraPauta(NovaPautaRequest novaPauta) {
        log.info("[inicia] PautaApplicationService - cadastraPauta ");
        log.info("[novaPauta] ", novaPauta);
        log.info("[finaliza] PautaApplicationService - cadastraPauta ");
        return null;
    }
}
