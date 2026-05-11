package com.aigis.ids.service;

import com.aigis.ids.configuration.ClientConfig;
import com.aigis.ids.entity.EnrichedAlert;
import com.aigis.ids.entity.RawAlert;
import com.aigis.ids.repository.EnrichedAlertRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MlService {
    private  final RestClient mlClient;
    private  final EnrichedAlertRepository enrichedAlertRepository;
    public MlService(@Qualifier("mlClient") RestClient mlClient,EnrichedAlertRepository enrichedAlertRepository){
        this.mlClient = mlClient;
        this.enrichedAlertRepository = enrichedAlertRepository;
    }
    public void sendTrafficData(RawAlert alert) {
        EnrichedAlert enrichedAlert = mlClient.post()
                .uri("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .body(alert)
                .retrieve()
                .body(EnrichedAlert.class);

        if (enrichedAlert != null) {
            System.out.println("Получен алерт: " + enrichedAlert.getVerdict());
            System.out.println(enrichedAlert);
            enrichedAlertRepository.save(enrichedAlert);
        }
    }
}
