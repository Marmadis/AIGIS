package com.aigis.ids.service;

import com.aigis.ids.entity.AbuseInfo;
import com.aigis.ids.entity.IBMXforceInfo;
import com.aigis.ids.entity.VirusTotalInfo;
import com.aigis.ids.repository.AbuseRepository;
import com.aigis.ids.repository.IBMXforceRepository;
import com.aigis.ids.repository.VirusTotalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class IPSearchInformationService {
    private final RestClient abuseClient;
    private final RestClient virusClient;
    private final RestClient ibmClient;

    private final AbuseRepository abuseRepo;
    private final VirusTotalRepository virusRepo;
    private final IBMXforceRepository ibmRepo;

    public IPSearchInformationService(
            @Qualifier("abuseIpDbClient") RestClient abuseClient,
            @Qualifier("virusTotalClient") RestClient virusClient,
            @Qualifier("ibmxforceClient") RestClient ibmClient,
            AbuseRepository abuseRepo,
            VirusTotalRepository virusRepo,
            IBMXforceRepository ibmRepo
    ) {
        this.abuseClient = abuseClient;
        this.virusClient = virusClient;
        this.ibmClient = ibmClient;
        this.abuseRepo = abuseRepo;
        this.virusRepo = virusRepo;
        this.ibmRepo = ibmRepo;
    }

    public void analyzeIP(String ip) {

        // 1. AbuseIPDB
        AbuseInfo abuse = abuseClient.get()
                .uri("/check?ipAddress={ip}", ip)
                .retrieve()
                .body(AbuseInfo.class);

        // 2. VirusTotal
        VirusTotalInfo vt = virusClient.get()
                .uri("/ip_addresses/{ip}", ip)
                .retrieve()
                .body(VirusTotalInfo.class);

        // 3. IBM X-Force
        IBMXforceInfo ibm = ibmClient.get()
                .uri("/{ip}", ip)
                .retrieve()
                .body(IBMXforceInfo.class);

        // 4. Сохранение
        abuseRepo.save(abuse);
        virusRepo.save(vt);
        ibmRepo.save(ibm);
    }
}
