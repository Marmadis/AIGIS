package com.aigis.ids.provider;

import com.aigis.ids.dto.AbuseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AbuseipdbProvider implements ThreatProvider{
    private final RestClient abuseIpDbClient;

    @Override
    public int getScore(String ipAddress) {
        try {
            AbuseResponse response = abuseIpDbClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/check")
                            .queryParam("ipAddress", ipAddress)
                            .build())
                    .retrieve()
                    .body(AbuseResponse.class);

            return response != null ? response.data().abuseConfidenceScore() : 0;
        } catch (Exception e) {
            System.err.println("Ошибка AbuseIPDB для IP " + ipAddress + ": " + e.getMessage());
            return 0;
        }
    }

    @Override
    public String getProviderName() {
        return "AbuseIPDB";
    }
}
