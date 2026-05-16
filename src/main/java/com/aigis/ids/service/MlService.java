package com.aigis.ids.service;

import com.aigis.ids.entity.*;
import com.aigis.ids.repository.EnrichedAlertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class MlService {
    private final RestClient mlClient;
    private final EnrichedAlertRepository enrichedAlertRepository;
    private final IPSearchInformationService ipSearchService;

    public MlService(@Qualifier("mlClient") RestClient mlClient,
                     EnrichedAlertRepository enrichedAlertRepository,
                     IPSearchInformationService ipSearchService) {
        this.mlClient = mlClient;
        this.enrichedAlertRepository = enrichedAlertRepository;
        this.ipSearchService = ipSearchService;
    }

    public void sendTrafficData(RawAlert alert) {
        try {
            // 1. Получаем данные (из БД или по API)
            AbuseInfo abuse = ipSearchService.getOrFetchAbuseInfo(alert.getSourceIp());
            VirusTotalInfo vt = ipSearchService.getOrFetchVTInfo(alert.getSourceIp());

            // 2. Проверяем на null, чтобы избежать NPE
            if (abuse == null || vt == null) {
                log.warn("Не удалось собрать полные данные для IP: {}", alert.getSourceIp());
            }

            // 4. Отправляем в ML
            EnrichedAlert enrichedAlert = mlClient.post()
                    .uri("/predict")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(alert)
                    .retrieve()
                    .body(EnrichedAlert.class);

            if (enrichedAlert != null) {
                log.info("Успешный вердикт ML: {}", enrichedAlert.getVerdict());
                enrichedAlertRepository.save(enrichedAlert);
            }

        } catch (Exception e) {
            log.error("Ошибка в процессе обогащения данных или ML: {}", e.getMessage());
        }
    }
}