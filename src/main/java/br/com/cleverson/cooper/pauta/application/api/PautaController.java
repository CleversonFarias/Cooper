package br.com.cleverson.cooper.pauta.application.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Controller
@Log4j2
@RequiredArgsConstructor
public class PautaController implements PautaAPI {

    private final PautaService pautaService;

    @Override
    public PautaCadastradaResponse cadastraPauta(NovaPautaRequest novaPauta) {
        log.info("[inicia] PautaController - cadastraPauta ");
        PautaCadastradaResponse pautaCadastrada = pautaService.cadastraPauta(novaPauta);
        log.info("[finaliza] PautaController - cadastraPauta ");
        return pautaCadastrada;
    }
}
