package br.com.cleverson.cooper.pauta.infra;

import br.com.cleverson.cooper.pauta.application.api.PautaRepository;
import br.com.cleverson.cooper.pauta.domain.Pauta;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PautaInfraRepository implements PautaRepository {
    private final PautaSpringDataJPARepository pautaSpringDataJPARepository;

    @Override
    public Pauta salva(Pauta pauta) {
        log.info("[inicia] PautaInfraRepository - salva ");
        pautaSpringDataJPARepository.save(pauta);
        log.info("[finaliza] PautaInfraRepository - salva ");
        return pauta;
    }
}
