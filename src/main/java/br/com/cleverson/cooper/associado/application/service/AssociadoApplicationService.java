package br.com.cleverson.cooper.associado.application.service;

import antlr.Token;
import br.com.cleverson.cooper.associado.infra.client.ConsultaCpfResponse;
import br.com.cleverson.cooper.associado.infra.client.SerproClientFeign;
import br.com.cleverson.cooper.sessaovotacao.application.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AssociadoApplicationService implements AssociadoService {
    private final SerproClientFeign serproClientFeign;

    @Override
    public void validaAssociadoAptoVoto(String cpfAssociado) {
        log.debug("[inicio] AssociadoApplicationService - validaAssociadoAptoVoto");
        ConsultaCpfResponse consultaCpfResponse = serproClientFeign.consultaCPF(TOKEN, cpfAssociado);
        valida(consultaCpfResponse);
        log.debug("[finaliza] AssociadoApplicationService - validaAssociadoAptoVoto");
    }

    private void valida(ConsultaCpfResponse consultaCpfResponse) {
        if (consultaCpfResponse.isInvalid()){
            throw new RuntimeException("CPF associado Invalido");
        }
    }
    private static final String TOKEN = "Bearer 06aef429-a981-3ec5-a1f8-71d38d86481e";
}
