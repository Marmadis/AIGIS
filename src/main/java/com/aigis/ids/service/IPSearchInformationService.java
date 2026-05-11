package com.aigis.ids.service;

import com.aigis.ids.entity.AbuseInfo;
import com.aigis.ids.entity.VirusTotalInfo;
import com.aigis.ids.repository.AbuseRepository;
import com.aigis.ids.repository.VirusTotalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class IPSearchInformationService {
    private final RestClient abuseClient;
    private final RestClient virusClient;
    private final AbuseRepository abuseRepo;
    private final VirusTotalRepository virusRepo;

    public IPSearchInformationService(
            @Qualifier("abuseIpDbClient") RestClient abuseClient,
            @Qualifier("virusTotalClient") RestClient virusClient,
            AbuseRepository abuseRepo,
            VirusTotalRepository virusRepo
    ) {
        this.abuseClient = abuseClient;
        this.virusClient = virusClient;
        this.abuseRepo = abuseRepo;
        this.virusRepo = virusRepo;
    }

    public void analyzeIP(String ip) {

        // 1. AbuseIPDB
        if(!abuseRepo.existsByIpAddress(ip)) {
            AbuseInfo abuse = abuseClient.get()
                    .uri("/check?ipAddress={ip}", ip)
                    .retrieve()
                    .body(AbuseInfo.class);

            abuseRepo.save(abuse);
            System.out.println(abuse);
        }else{
            System.out.println("[Abuse]Information about IP:"+ip+" already exist");
        }
        // 2. VirusTotal
        if (!virusRepo.existsByIpAddress(ip)) {
            VirusTotalInfo vt = virusClient.get()
                    .uri("/ip_addresses/{ip}", ip)
                    .retrieve()
                    .body(VirusTotalInfo.class);
            virusRepo.save(vt);
            System.out.println(vt);
        } else{
            System.out.println("[VirusTotal]Information about IP:"+ip+" already exist");
        }

    }



}
