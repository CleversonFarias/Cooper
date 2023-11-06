package br.com.cleverson.cooper.pauta.infra;

import br.com.cleverson.cooper.pauta.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PautaSpringDataJPARepository extends JpaRepository <Pauta, UUID> {
}
