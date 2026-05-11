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
        // 3. IBM X-Force
        if(!ibmRepo.existsByIpAddress(ip)) {
            IBMXforceInfo ibm = ibmClient.get()
                    .uri("/{ip}", ip)
                    .retrieve()
                    .body(IBMXforceInfo.class);
            ibmRepo.save(ibm);
            System.out.println(ibm);
        }
        else{
            System.out.println("[IBM X-Force]Information about IP:"+ip+" already exist");
        }
        // 4. Сохранение

    }



}
