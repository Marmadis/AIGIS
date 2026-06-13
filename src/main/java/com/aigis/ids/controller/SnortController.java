package com.aigis.ids.controller;

import com.aigis.ids.entity.RawAlert;
import com.aigis.ids.repository.RawAlertRepository;
import com.aigis.ids.service.IPSearchInformationService;
import com.aigis.ids.service.MlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
    @RestController
    @RequestMapping("/webhook")
    @RequiredArgsConstructor
    public class SnortController {

        private final RawAlertRepository alertRepository;

        private final IPSearchInformationService ipSearchInformationService;

        private  final MlService mlService;

        @PostMapping("/snort")
        public ResponseEntity<String> snortAlert(@RequestBody RawAlert alert) {
            alertRepository.save(alert);
            mlService.sendTrafficData(alert);
            log.info("Получен новый alert! "+alert);
            return ResponseEntity.ok("Received");
        }

       /* public boolean isDuplicate(RawAlert newAlert, RawAlert oldAlert) {

            Duration diff = Duration.between(
                    oldAlert.getTimestamp(),
                    newAlert.getTimestamp()
            );

            return newAlert.getSourceIp().equals(oldAlert.getSourceIp())
                    && newAlert.getDestinationIp().equals(oldAlert.getDestinationIp())
                    && newAlert.getEventName().equals(oldAlert.getEventName())
                    && diff.toMinutes() < 5;
        } */

}
