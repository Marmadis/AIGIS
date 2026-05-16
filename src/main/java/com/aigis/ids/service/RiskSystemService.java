package com.aigis.ids.service;

import com.aigis.ids.entity.AbuseInfo;
import com.aigis.ids.entity.RawAlert;
import com.aigis.ids.entity.VirusTotalInfo;
import com.aigis.ids.repository.AbuseRepository;
import com.aigis.ids.repository.IocRepository;
import com.aigis.ids.repository.VirusTotalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.springframework.data.elasticsearch.annotations.DateFormat.time;

@Slf4j
@Service
public class RiskSystemService {

    private final IPSearchInformationService ipSearchInformationService;
    private final AbuseRepository abuseRepository;
    private final VirusTotalRepository virusTotalRepository;
    private final IocRepository iocRepository;

    public  RiskSystemService(IPSearchInformationService ipSearchInformationService,
                              AbuseRepository abuseRepository,
                              VirusTotalRepository virusTotalRepository,
                              IocRepository iocRepository){
        this.ipSearchInformationService = ipSearchInformationService;
        this.abuseRepository = abuseRepository;
        this.virusTotalRepository = virusTotalRepository;
        this.iocRepository = iocRepository;
    }


    public int parseSafeInt(String value){
        if(value.trim().isBlank() || value == null){
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.error("Risk System Service :"+e.getMessage());
            return 0;
        }

    }
}
