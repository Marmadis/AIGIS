package com.aigis.ids.service;

import com.aigis.ids.configuration.ClientConfig;
import com.aigis.ids.entity.AbuseInfo;
import com.aigis.ids.entity.EnrichedAlert;
import com.aigis.ids.entity.RawAlert;
import com.aigis.ids.entity.VirusTotalInfo;
import com.aigis.ids.repository.AbuseRepository;
import com.aigis.ids.repository.EnrichedAlertRepository;
import com.aigis.ids.repository.VirusTotalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MlService {
    private  final RestClient mlClient;
    private  final EnrichedAlertRepository enrichedAlertRepository;
    private  final AbuseRepository abuseRepository;
    private  final VirusTotalRepository virusTotalRepository;
    public MlService(@Qualifier("mlClient") RestClient mlClient,
                     EnrichedAlertRepository enrichedAlertRepository,
                     VirusTotalRepository virusTotalRepository,
                     AbuseRepository abuseRepository){
        this.mlClient = mlClient;
        this.enrichedAlertRepository = enrichedAlertRepository;
        this.abuseRepository = abuseRepository;
        this.virusTotalRepository = virusTotalRepository;
    }
    public void sendTrafficData(RawAlert alert) {
        if(abuseRepository.findByIpAddress(alert.getSourceIp()) == null){
           System.out.println("[AbuseRepository] Нет данных по этому адресу");
        }

        if(virusTotalRepository.findByIpAddress(alert.getSourceIp()) == null){
            System.out.println("[VirusTotalRepository] Нет данных по этому адресу");
        }

        AbuseInfo abuse= abuseRepository.findByIpAddress(alert.getSourceIp());
        VirusTotalInfo vt = virusTotalRepository.findByIpAddress(alert.getSourceIp());


        EnrichedAlert enrichedAlert = mlClient.post()
                .uri("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .body(alert)
                .body(abuse.getAbuseConfidenceScore())
                .body(abuse.isTor())
                .body(abuse.getTotalReports())
                .body(vt.getLastAnalysisStats().getMalicious())
                .retrieve()
                .body(EnrichedAlert.class);

        if (enrichedAlert != null) {
            System.out.println("Получен алерт: " + enrichedAlert.getVerdict());
            System.out.println(enrichedAlert);
            enrichedAlertRepository.save(enrichedAlert);
        }
    }
}
