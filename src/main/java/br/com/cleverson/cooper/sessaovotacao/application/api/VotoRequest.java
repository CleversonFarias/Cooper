package br.com.cleverson.cooper.sessaovotacao.application.api;

import br.com.cleverson.cooper.sessaovotacao.domain.OpcaoVoto;
import lombok.Getter;
import lombok.ToString;

import java.security.PrivateKey;

@Getter
@ToString
public class VotoRequest {
    private String cpfAssociado;
    private OpcaoVoto opcaoVoto;
}
