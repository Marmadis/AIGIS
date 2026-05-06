package com.aigis.ids.controller;

import com.aigis.ids.entity.RawAlert;
import com.aigis.ids.repository.RawAlertRepository;
import com.aigis.ids.service.RiskSystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @Slf4j
    @RestController
    @RequestMapping("/webhook")
    public class SnortController {

        private final RawAlertRepository alertRepository;

        private final RiskSystemService riskSystemService;

        public SnortController(RawAlertRepository alertRepository, RiskSystemService riskSystemService) {
            this.alertRepository = alertRepository;
            this.riskSystemService = riskSystemService;
        }

        @PostMapping("/snort")
        public ResponseEntity<String> snortAlert(@RequestBody RawAlert alert) {
            alertRepository.save(alert);
            riskSystemService.riskCalculate(alert);
            System.out.println(alert);
            return ResponseEntity.ok("Received");
        }

}
