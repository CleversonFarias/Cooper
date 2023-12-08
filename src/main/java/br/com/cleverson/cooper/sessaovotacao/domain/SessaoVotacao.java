package br.com.cleverson.cooper.sessaovotacao.domain;

import br.com.cleverson.cooper.pauta.domain.Pauta;
import br.com.cleverson.cooper.sessaovotacao.application.api.ResultadoSessaoResponse;
import br.com.cleverson.cooper.sessaovotacao.application.api.SessaoAberturaRequest;
import br.com.cleverson.cooper.sessaovotacao.application.api.VotoRequest;
import br.com.cleverson.cooper.sessaovotacao.application.service.AssociadoService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private UUID id;
    private UUID idPauta;
    private Integer tempoDuracao;
    @Enumerated(EnumType.STRING)
    private StatusSessaoVotacao status;
    private LocalDateTime momentoAbertura;
    private LocalDateTime momentoEncerramento;
    @OneToMany(mappedBy = "sessaoVotacao",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @MapKey(name = "cpfAssociado")
    private Map<String, VotoPauta> votos;

    public SessaoVotacao(SessaoAberturaRequest sessaoAberturaRequest, Pauta pauta) {
        this.idPauta = pauta.getId();
        this.tempoDuracao = sessaoAberturaRequest.getTempoDuracao().orElse(1);
        this.momentoAbertura = LocalDateTime.now();
        this.momentoEncerramento = momentoAbertura.plusMinutes(this.tempoDuracao);
        this.status = StatusSessaoVotacao.ABERTA;
        this.votos = new HashMap<>();
    }


    public VotoPauta recebeVoto(VotoRequest votoRequest, AssociadoService associadoService, PublicadorResultadoSessao publicadorResultadoSessao) {
        validaSessaoAberta(this, publicadorResultadoSessao);
        validaAssociado(votoRequest.getCpfAssociado(), associadoService);
        VotoPauta voto = new VotoPauta(this, votoRequest);
        votos.put(votoRequest.getCpfAssociado(), voto);
        return voto;
    }

    private void validaSessaoAberta(SessaoVotacao sessaoVotacao, PublicadorResultadoSessao publicadorResultadoSessao) {
        atualizaStatus(publicadorResultadoSessao);
        if (this.status.equals(StatusSessaoVotacao.FECHADA)) {
            throw new RuntimeException("Sessão Está Fechada");
        }
    }

    private void atualizaStatus(PublicadorResultadoSessao publicadorResultadoSessao) {
        if (this.status.equals(StatusSessaoVotacao.ABERTA)) {
            if (LocalDateTime.now().isAfter(this.momentoEncerramento)) {
                fechaSessão(publicadorResultadoSessao);
            }
        }
    }

    private void fechaSessão(PublicadorResultadoSessao publicadorResultadoSessao) {
        this.status = StatusSessaoVotacao.FECHADA;
        publicadorResultadoSessao.publica(new ResultadoSessaoResponse(this));
    }

    private void validaAssociado(String cpfAssociado, AssociadoService associadoService) {
        associadoService.validaAssociadoAptoVoto(cpfAssociado);
        validaVotoDuplicado(cpfAssociado);
    }

    private void validaVotoDuplicado(String cpfAssociado) {
        if (this.votos.containsKey(cpfAssociado)) {
           throw new RuntimeException("Associado Já Votou Nessa Sessão!");
        }
    }

    public ResultadoSessaoResponse resultadoSessao(PublicadorResultadoSessao publicadorResultadoSessao) {
        atualizaStatus(publicadorResultadoSessao);
        return new ResultadoSessaoResponse(this);
    }

    public Long getTotalVotos() {
        return Long.valueOf(this.votos.size());
    }

    public Long getTotalSim() {
        return calculaVotosPorOpcao(OpcaoVoto.SIM);
    }

    public Long getTotalNao() {
        return calculaVotosPorOpcao(OpcaoVoto.NAO);
    }

    private Long calculaVotosPorOpcao(OpcaoVoto opcao) {
        return votos.values().stream()
                .filter(voto -> voto.opcaoIgual(opcao) )
                .count();
    }
}
