package br.com.cleverson.cooper.sessaovotacao.application.api;

import br.com.cleverson.cooper.sessaovotacao.domain.VotoPauta;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Getter
public class VotoResponse {
    private UUID idVoto;
    private UUID idSessao;
    private String cpfAssociado;
    private LocalDateTime momentoVoto;

 public VotoResponse(VotoPauta voto) {
  this.idVoto = voto.getIdVoto();
  this.idSessao = voto.getIdSessao();
  this.cpfAssociado = voto.getCpfAssociado();
  this.momentoVoto = voto.getMomentoVoto();
 }
}
