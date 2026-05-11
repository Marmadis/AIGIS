package com.aigis.ids.service;

import com.aigis.ids.entity.AbuseInfo;
import com.aigis.ids.entity.VirusTotalInfo;
import com.aigis.ids.repository.AbuseRepository;
import com.aigis.ids.repository.VirusTotalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Async
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
            try {
                AbuseInfo.AbuseContainer container = abuseClient.get()
                        .uri("/check?ipAddress={ip}", ip)
                        .retrieve()
                        .body(AbuseInfo.AbuseContainer.class);

                if (container != null && container.getData() != null) {
                    AbuseInfo abuse = container.getData();
                    abuseRepo.save(abuse);
                    System.out.println(abuse);
                } else {
                    System.out.println("[AbuseIPDB] Ответ от сервиса пустой");
                }
            } catch (Exception e){
                System.out.println("[AbuseIPDB API Request]Error:"+e.getMessage());
            }
        }else{
            System.out.println("[Abuse]Information about IP:"+ip+" already exist");
        }
        // 2. VirusTotal
        if (!virusRepo.existsByIpAddress(ip)) {
            try {
                VirusTotalInfo.VTContainer container = virusClient.get()
                        .uri("/ip_addresses/{ip}", ip)
                        .retrieve()
                        .body(VirusTotalInfo.VTContainer.class);
                if (container != null && container.getData() != null) {
                    VirusTotalInfo vt = container.getData().getAttributes();
                    vt.setIpAddress(ip);
                    virusRepo.save(vt);
                    System.out.println(vt);
                } else {
                    System.out.println("[VirusTotal] Ответ от сервиса пустой");
                }
            }catch (Exception e){
                System.out.println("[VirusTotal API Request]Error:"+e.getMessage());
            }
        } else{
            System.out.println("[VirusTotal]Information about IP:"+ip+" already exist");
        }

    }



}
