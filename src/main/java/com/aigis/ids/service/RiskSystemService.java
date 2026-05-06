package com.aigis.ids.service;

import com.aigis.ids.entity.AbuseInfo;
import com.aigis.ids.entity.IBMXforceInfo;
import com.aigis.ids.entity.RawAlert;
import com.aigis.ids.entity.VirusTotalInfo;
import com.aigis.ids.repository.AbuseRepository;
import com.aigis.ids.repository.IBMXforceRepository;
import com.aigis.ids.repository.VirusTotalRepository;
import org.springframework.stereotype.Service;

import static org.springframework.data.elasticsearch.annotations.DateFormat.time;

@Service
public class RiskSystemService {

    private final IPSearchInformationService ipSearchInformationService;
    private final AbuseRepository abuseRepository;
    private  final IBMXforceRepository ibmXforceRepository;
    private final VirusTotalRepository virusTotalRepository;

    public  RiskSystemService(IPSearchInformationService ipSearchInformationService,
                              AbuseRepository abuseRepository,
                              IBMXforceRepository ibmXforceRepository,
                              VirusTotalRepository virusTotalRepository){
        this.ipSearchInformationService = ipSearchInformationService;
        this.abuseRepository = abuseRepository;
        this.ibmXforceRepository = ibmXforceRepository;
        this.virusTotalRepository = virusTotalRepository;
    }

    public void riskCalculate(RawAlert rawAlert){
        ipSearchInformationService.analyzeIP(rawAlert.getDestinationIp());
         waitThreeMinutes();
         ipSearchInformationService.analyzeIP(rawAlert.getSourceIp());
         waitThreeMinutes();

         AbuseInfo abuseInfoSource = abuseRepository.findByIpAddress(rawAlert.getSourceIp());
         AbuseInfo abuseInfoDestination = abuseRepository.findByIpAddress(rawAlert.getDestinationIp());


         int abuseSourceScore = abuseInfoSource.getTotalReports();
        int abuseDestinationScore = abuseInfoDestination.getTotalReports();
        VirusTotalInfo virusTotalInfoSource = virusTotalRepository.findByIpAddress(rawAlert.getSourceIp());
        VirusTotalInfo virusTotalInfoDestination = virusTotalRepository.findByIpAddress(rawAlert.getDestinationIp());

        int virusTotalSourceScore = parseSafeInt(virusTotalInfoSource.getLast_analysis_stats());
        int virusTotalDestinationScore =parseSafeInt(virusTotalInfoDestination.getLast_analysis_stats());

        IBMXforceInfo ibmXforceInfoSource = ibmXforceRepository.findByIpAddress(rawAlert.getSourceIp());
        IBMXforceInfo ibmXforceInfoDestination = ibmXforceRepository.findByIpAddress(rawAlert.getDestinationIp());

        int ibmSourceScore = parseSafeInt(ibmXforceInfoSource.getSubscore());
        int ibmDestinationScore =parseSafeInt(ibmXforceInfoDestination.getSubscore());

        double sourceRisk = ( (abuseSourceScore/100)*0.3+
               (virusTotalSourceScore/94)*0.25+(ibmSourceScore/10)*0.2);

        double destinationRisk = ( (abuseDestinationScore/100)*0.3+
                (virusTotalDestinationScore/94)*0.25+(ibmDestinationScore/10)*0.2);

    }

    public int parseSafeInt(String value){
        if(value.trim().isBlank() || value == null){
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("Something went wrong"+e.getMessage());
            return 0;
        }

    }
    public void waitThreeMinutes(){
        try {
            time.wait(180000);
        } catch (InterruptedException e){
            System.out.println("Something went wrong in waiting"+e.getMessage());
        }
    }
}
