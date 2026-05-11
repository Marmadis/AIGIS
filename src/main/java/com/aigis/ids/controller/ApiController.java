package com.aigis.ids.controller;

import com.aigis.ids.service.APIKeyManagerService;
import com.aigis.ids.service.IPSearchInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final APIKeyManagerService apiKeyManagerService;
    private final IPSearchInformationService ipSearchInformationService;

    public ApiController(APIKeyManagerService apiKeyManagerService,IPSearchInformationService ipSearchInformationService) {
        this.apiKeyManagerService = apiKeyManagerService;
        this.ipSearchInformationService = ipSearchInformationService;
    }

    @PostMapping("/keys/abuse")
    public ResponseEntity<String> updateAbuseKey(@RequestBody String newKey) {
        if (newKey == null || newKey.isBlank()){
            return ResponseEntity.badRequest().body("Key cannot be empty");
        }
        apiKeyManagerService.setAbuseKey(newKey.trim());
        return ResponseEntity.ok("Abuse API added successfully");
    }

    @PostMapping("/key/virustotal")
    public ResponseEntity<String> updateVirusTotalKey(@RequestBody String newKey){
        if (newKey == null || newKey.isBlank()){
            return ResponseEntity.badRequest().body("Key cannot be empty");
        }
        apiKeyManagerService.setVirusTotalKey(newKey.trim());
        return ResponseEntity.ok("VirusTotal API added successfully");
    }

    @PostMapping("/key/ibmxforce")
    public ResponseEntity<String> updateIBMXforceKey(@RequestBody String newKey){
        if (newKey == null || newKey.isBlank()){
            return ResponseEntity.badRequest().body("Key cannot be empty");
        }
        apiKeyManagerService.setIBMXForceKey(newKey.trim());
        return  ResponseEntity.ok("IBM X-Force API added successfully");
    }

    @PostMapping("/password/ibmxforce")
    public ResponseEntity<String> updateIBMXforcePassword(@RequestBody String newPassword){
        if (newPassword == null || newPassword.isBlank()){
            return ResponseEntity.badRequest().body("Password cannot be empty");
        }
        apiKeyManagerService.setDefaultIBMXforcePassword(newPassword.trim());
        return  ResponseEntity.ok("IBM X-Force Password added successfully");
    }

    @PostMapping("/search/{ip}")
    public ResponseEntity<String> searchTest(@PathVariable String ip){
        ipSearchInformationService.analyzeIP(ip);
        return ResponseEntity.ok("OK");
    }
}
