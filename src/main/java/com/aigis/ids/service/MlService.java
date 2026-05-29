package com.aigis.ids.service;

import com.aigis.ids.dto.NetworkFeaturesDTO;
import com.aigis.ids.entity.*;
import com.aigis.ids.repository.AbuseRepository;
import com.aigis.ids.repository.EnrichedAlertRepository;
import com.aigis.ids.repository.IocRepository;
import com.aigis.ids.repository.VirusTotalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class MlService {
    private final RestClient mlClient;
    private final EnrichedAlertRepository enrichedAlertRepository;
    private final IPSearchInformationService ipSearchService;

    private  final AbuseRepository abuseRepository;

    private  final VirusTotalRepository virusTotalRepository;

    private  final CollectDataService collectDataService;

    private  final IocRepository iocRepository;
    public void sendTrafficData(RawAlert alert) {
        try {
            // 1. Получаем данные (из БД или по API)
            AbuseInfo abuseSource = abuseRepository.findByIpAddress(alert.getSourceIp()).
                    orElseThrow(() -> new Exception("Информация об AbuseInfo не найдена для IP: " +alert.getSourceIp()));
            VirusTotalInfo vtSource = virusTotalRepository.findByIpAddress(alert.getSourceIp())
                    .orElseThrow(() -> new Exception("Информация об VirusTotalInfo не найдена для IP: " +alert.getSourceIp()));

            AbuseInfo abuseDestination = abuseRepository.findByIpAddress(alert.getSourceIp()).
                    orElseThrow(() -> new Exception("Информация об AbuseInfo не найдена для IP: " +alert.getDestinationIp()));
            VirusTotalInfo vtDestination = virusTotalRepository.findByIpAddress(alert.getSourceIp())
                    .orElseThrow(() -> new Exception("Информация об VirusTotalInfo не найдена для IP: " +alert.getDestinationIp()));


            log.info("Компилация данных для отправки  ML  всех признаков");
            NetworkFeaturesDTO networkFeaturesDTO = collectDataService.findByAlert(alert,
                    abuseSource,abuseDestination,vtSource,vtDestination);


            // 4. Отправляем в ML
            EnrichedAlert enrichedAlert = mlClient.post()
                    .uri("/predict")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(networkFeaturesDTO)
                    .retrieve()
                    .body(EnrichedAlert.class);

            if (enrichedAlert != null) {
                log.info("Успешный вердикт ML: Alert is :{} and confidence score: {}", enrichedAlert.getEvaluation(),
                        enrichedAlert.getConfidenceScore());
                enrichedAlertRepository.save(enrichedAlert);
                if(enrichedAlert.getIoc() != null){
                    log.info("Обнаружен индикатор компроментации:"+enrichedAlert.getIoc());
                    if(iocRepository.existsByIpAddress(enrichedAlert.getIoc())){
                        log.info("Данный индикатор уже существует в ElasticSearch");
                    }else{
                        log.info("Добавляем новый индникатор в ElasticSearch");
                        IndicatorOfCompromise indicatorOfCompromise = new IndicatorOfCompromise();
                        indicatorOfCompromise.setAlertId(enrichedAlert.getId());
                        indicatorOfCompromise.setIpAddress(enrichedAlert.getIoc());
                        indicatorOfCompromise.setMessage("Source IP Verdict:"+enrichedAlert.getDetails().getSource().getVerdict()+"" +
                                " | Destination IP Verdict"+enrichedAlert.getDetails().getSource().getVerdict());
                        iocRepository.save(indicatorOfCompromise);
                    }
                }
            }

        } catch (Exception e) {
            log.error("Ошибка в процессе обогащения данных или ML: {}", e.getMessage());
        }
    }
}