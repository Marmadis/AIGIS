package com.aigis.ids.service;

import com.aigis.ids.dto.NetworkFeaturesDTO;
import com.aigis.ids.entity.*;
import com.aigis.ids.mapper.NetworkFeaturesMapper;
import com.aigis.ids.repository.ConnLogZeekRepository;
import com.aigis.ids.repository.FlowMeterLogZeekRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectDataService {

    private  final ConnLogZeekRepository connLogZeekRepository;
    private  final FlowMeterLogZeekRepository flowMeterLogZeekRepository;

    private  final  NetworkFeaturesMapper networkFeaturesMapper;


     public NetworkFeaturesDTO findByAlert(RawAlert rawAlert,
                                           AbuseInfo abuseSource,
                                           AbuseInfo abuseDestination,
                                           VirusTotalInfo vtSource,
                                           VirusTotalInfo vtDestination){
        String sourceIp = rawAlert.getSourceIp();
        String sourcePort = rawAlert.getSourcePort();
        String destinationIp = rawAlert.getDestinationIp();
        String destiantionPort = rawAlert.getDestinationPort();

        log.info("Поиск сетевого лога связанного с алертом ID"+rawAlert.getId());
         ConnLogZeek connLogZeek = connLogZeekRepository.findFirstBySourceIpAndSourcePortAndDestinationIpAndDestinationPortOrDestinationIpAndDestinationPortAndSourceIpAndSourcePort(
                 sourceIp, sourcePort, destinationIp, destiantionPort,
                 destinationIp, destiantionPort, sourceIp, sourcePort
         ).orElseThrow(() -> new RuntimeException("Сетевой лог Zeek не найден для соединения"));

         log.info("Поиск признаков flowmeter по uid "+connLogZeek.getUid());
         FlowMeterLogZeek flowMeterLogZeek = flowMeterLogZeekRepository.findByUid(connLogZeek.getUid())
                 .orElseThrow(() -> new RuntimeException("FlowMeter лог Zeek не найден для соединения"));

         log.info("Маппинг данных в NetworkFeatures");
         return networkFeaturesMapper.toNetworkFeaturesDTO(flowMeterLogZeek,
                 rawAlert.getSourceIp(),
                 rawAlert.getSourcePort(),
                 rawAlert.getDestinationIp(),
                 rawAlert.getDestinationPort(),
                 abuseSource,vtSource,abuseDestination,vtDestination);
     }
}
