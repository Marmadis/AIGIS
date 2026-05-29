package com.aigis.ids.service;

import com.aigis.ids.entity.AbuseInfo;
import com.aigis.ids.entity.VirusTotalInfo;
import com.aigis.ids.repository.AbuseRepository;
import com.aigis.ids.repository.VirusTotalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
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
            VirusTotalRepository virusRepo) {
        this.abuseClient = abuseClient;
        this.virusClient = virusClient;
        this.abuseRepo = abuseRepo;
        this.virusRepo = virusRepo;
    }

    public AbuseInfo getOrFetchAbuseInfo(String ip) {
        return abuseRepo.findByIpAddress(ip).orElseGet(() -> {
            try {
                log.info("Запрос AbuseIPDB для IP: {}", ip);
                AbuseInfo.AbuseContainer container = abuseClient.get()
                        .uri("/check?ipAddress={ip}", ip)
                        .retrieve()
                        .body(AbuseInfo.AbuseContainer.class);

                if (container != null && container.getData() != null) {
                    log.info("Обнаружены данные в базе AbuseIPDB сохраняем их(IP:{}) | ID {}",ip,container.getData().getId());
                    return abuseRepo.save(container.getData());
                }
                log.info("Ответ от AbuseIPDB пустой  по IP:{}",ip);
            } catch (Exception e) {
                log.error("Ошибка AbuseIPDB API для {}: {}", ip, e.getMessage());
            }
            return null;
        });
    }

    public VirusTotalInfo getOrFetchVTInfo(String ip) {
        return virusRepo.findByIpAddress(ip).orElseGet(() -> {
            try {
                log.info("Запрос VirusTotal для IP: {}", ip);
                VirusTotalInfo.VTContainer container = virusClient.get()
                        .uri("/ip_addresses/{ip}", ip)
                        .retrieve()
                        .body(VirusTotalInfo.VTContainer.class);

                if (container != null && container.getData() != null) {
                    VirusTotalInfo vt = container.getData().getAttributes();
                    vt.setIpAddress(ip);
                    log.info("Обнаружены данные в базе VirusTotal сохраняем их(IP:{}) | ID {}",ip,vt.getId());
                    return virusRepo.save(vt);
                }
                log.info("Ответ от VirusTotal пустой  по IP:{}",ip);
            } catch (Exception e) {
                log.error("Ошибка VirusTotal API для {}: {}", ip, e.getMessage());
            }
            return null;
        });
    }

    public boolean isPublicIp(String ip){

        try{
            InetAddress address = InetAddress.getByName(ip);
            return  !address.isSiteLocalAddress() &&
                    !address.isLoopbackAddress() &&
                    !address.isLinkLocalAddress();
        }catch (UnknownHostException e){
            log.error("Incorrect address:"+e.getMessage());
            return false;
        }
    }

    public void analyzeIP(String ip){

        if (isPublicIp(ip)){
            log.info("Адрес является публичным:"+ip);
            log.info("Идет проверка адреса через сервисы AbuseIPDB & VirusTotal");
            getOrFetchVTInfo(ip);
            getOrFetchAbuseInfo(ip);
        }else{
            log.warn("Адрес является приватным .Данные в Elasticsearch будут заполнены соотвуетющие .Проверки через сервисы AbuseIPDB & VirusTotal пропускаются");

        }
    }
}