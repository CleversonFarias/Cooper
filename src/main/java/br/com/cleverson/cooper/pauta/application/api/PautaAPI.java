package br.com.cleverson.cooper.pauta.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pauta")
public interface PautaAPI {
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    PautaCadastradaResponse cadastraPauta(@RequestBody @Valid NovaPautaRequest novaPauta);
}
