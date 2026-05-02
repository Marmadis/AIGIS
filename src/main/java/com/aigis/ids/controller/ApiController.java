package com.aigis.ids.controller;

import com.aigis.ids.service.APIKeyManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final APIKeyManagerService apiKeyManagerService;

    public ApiController(APIKeyManagerService apiKeyManagerService) {
        this.apiKeyManagerService = apiKeyManagerService;
    }

    @PostMapping("/keys/abuse")
    public ResponseEntity<String> updateAbuseKey(@RequestBody String newKey) {
        apiKeyManagerService.setAbuseKey(newKey);
        return ResponseEntity.ok("Abuse API added successfully");
    }

    @PostMapping("/key/virustotal")
    public ResponseEntity<String> updateVirusTotalKey(@RequestBody String newKey){
        apiKeyManagerService.setVirusTotalKey(newKey);
        return ResponseEntity.ok("VirusTotal API added successfully");
    }

    @PostMapping("/key/ibmxforce")
    public ResponseEntity<String> updateIBMXforce(@RequestBody String newKey){
        apiKeyManagerService.setIBMXForceKey(newKey);
        return  ResponseEntity.ok("IBM X-Force API added successfully");
    }
}
