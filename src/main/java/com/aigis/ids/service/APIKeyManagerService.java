package com.aigis.ids.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class APIKeyManagerService {
    private final Map<String, String> keys = new ConcurrentHashMap<>();


    @Value("${abuseipdb.api.key}")
    private String defaultAbuseKey;

    @Value("${virustotal.api.key}")
    private String defaultVirusTotalKey;

    @Value("${ibmxforce.api.key}")
    private String defaultIBMXforceKey;

    @PostConstruct
    public void init() {
        keys.put("ABUSE", defaultAbuseKey);
        keys.put("VIRUSTOTAL",defaultVirusTotalKey);
        keys.put("IBMXFORCE",defaultIBMXforceKey);
    }

    public String getAbuseKey() { return keys.get("ABUSE"); }
    public void setAbuseKey(String newKey) { keys.put("ABUSE", newKey); }

    public String getVirusTotalKey() { return keys.get("VIRUSTOTAL"); }
    public void setVirusTotalKey(String newKey) { keys.put("VIRUSTOTAL", newKey); }

    public String getIBMXForceKey() { return keys.get("IBMXFORCE"); }
    public void setIBMXForceKey(String newKey) { keys.put("IBMXFORCE", newKey); }
}
