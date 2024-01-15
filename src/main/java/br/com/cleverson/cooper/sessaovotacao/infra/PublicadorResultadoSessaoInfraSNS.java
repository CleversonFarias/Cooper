package br.com.cleverson.cooper.sessaovotacao.infra;

import br.com.cleverson.cooper.config.AwsConfigProperties;
import br.com.cleverson.cooper.sessaovotacao.application.api.ResultadoSessaoResponse;
import br.com.cleverson.cooper.sessaovotacao.domain.PublicadorResultadoSessao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class PublicadorResultadoSessaoInfraSNS implements PublicadorResultadoSessao {
    private final NotificationMessagingTemplate publicadorResultadoSNS;
    private final AwsConfigProperties awsConfigProperties;

    @Override
    public void publica(ResultadoSessaoResponse resultadoSessaoResponse) {
        log.debug("´[inicio] PublicadorResultadoSessaoInfraSNS - publica");
        publicadorResultadoSNS.sendNotification(awsConfigProperties.getResultadoSessaoTopic(),resultadoSessaoResponse,resultadoSessaoResponse.getIdPauta().toString());
        log.debug("´[finaliza] PublicadorResultadoSessaoInfraSNS - publica");
    }
}
