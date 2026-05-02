package com.aigis.ids.controller;

import com.aigis.ids.entity.Alert;
import com.aigis.ids.repository.AlertRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/webhook")
    public class SnortController {

        private final AlertRepository alertRepository;

        public SnortController(AlertRepository alertRepository) {
            this.alertRepository = alertRepository;
        }

        @PostMapping("/snort")
        public ResponseEntity<String> snortAlert(@RequestBody Alert alert) {
            alertRepository.save(alert);
            System.out.println(alert);
            return ResponseEntity.ok("Received");
        }

}
