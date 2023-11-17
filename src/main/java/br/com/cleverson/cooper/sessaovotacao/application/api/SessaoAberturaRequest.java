package br.com.cleverson.cooper.sessaovotacao.application.api;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;
@Getter
@ToString
public class SessaoAberturaRequest {
    @NotNull
    private UUID idPauta;
    private Integer tempoDuracao;

    public Optional<Integer> getTempoDuracao(){
        return Optional.ofNullable(this.tempoDuracao);
    }
}
