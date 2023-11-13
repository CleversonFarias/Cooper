package br.com.cleverson.cooper.sessaovotacao.domain;

import br.com.cleverson.cooper.pauta.domain.Pauta;
import br.com.cleverson.cooper.sessaovotacao.application.api.SessaoAberturaRequest;
import br.com.cleverson.cooper.sessaovotacao.application.api.VotoRequest;
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
    private LocalDateTime dataAbertura;
    private LocalDateTime dataEncerramento;
    @OneToMany(mappedBy = "sessaoVotacao",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @MapKey(name = "cpfAssociado")
    private Map<String, VotoPauta> votos;

    public SessaoVotacao(SessaoAberturaRequest sessaoAberturaRequest, Pauta pauta) {
        this.idPauta = pauta.getId();
        this.tempoDuracao = sessaoAberturaRequest.getTempoDuracao().orElse(1);
        this.dataAbertura = LocalDateTime.now();
        this.dataEncerramento = dataAbertura.plusMinutes(this.tempoDuracao);
        this.status = StatusSessaoVotacao.ABERTA;
        this.votos = new HashMap<>();
    }

    public VotoPauta recebeVoto(VotoRequest votoRequest) {
        validaSessaoAberta(this);
        validaAssociado(votoRequest.getCpfAssociado());
        VotoPauta voto = new VotoPauta(this, votoRequest);
        votos.put(votoRequest.getCpfAssociado(), voto);
        return voto;
    }

    private void validaSessaoAberta(SessaoVotacao sessaoVotacao) {
      atualizaStatus();
       if (this.status.equals(StatusSessaoVotacao.FECHADA)){
          throw new RuntimeException("Sessão Está Fechada");
       };
    }

    private void atualizaStatus() {
    if (this.status.equals(StatusSessaoVotacao.ABERTA)){
        if (LocalDateTime.now().isAfter(this.dataEncerramento)){
            fechaSessão();
        }
    }
    }

    private void fechaSessão() {
        this.status = StatusSessaoVotacao.FECHADA;
    }

    private void validaAssociado(String cpfAssociado) {
         if (this.votos.containsKey(cpfAssociado)) {
             new RuntimeException("Associado Já Votou Nessa Sessão!");
         }
    }
}
