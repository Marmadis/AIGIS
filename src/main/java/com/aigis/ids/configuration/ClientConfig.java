package com.aigis.ids.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {


        @Value("${abuseipdb.api.key}")
        private String apiKeyAbuse;

        @Value("${virustotal.api.key}")
        private String apiKeyVirusTotal;

        @Value("${ibmxforce.api.key}")
        private String apiKeyIBMXforce;

        @Bean
        public RestClient abuseIpDbClient() {
            return RestClient.builder()
                    .baseUrl("https://api.abuseipdb.com/api/v2")
                    .defaultHeader("Key", apiKeyAbuse)
                    .defaultHeader("Accept", "application/json")
                    .build();
        }

        @Bean
        public RestClient virusTotalClient(){
                return RestClient.builder()
                        .baseUrl("https://www.virustotal.com/api/v3")
                        .defaultHeader("Key",apiKeyVirusTotal)
                        .defaultHeader("Accept", "application/json")
                        .build();
        }

        @Bean
        public RestClient ibmxforceClient(){
                return RestClient.builder()
                        .baseUrl("https://api.xforce.ibmcloud.com/ipr/")
                        .defaultHeader("Key",apiKeyIBMXforce)
                        .defaultHeader("Accept", "application/json")
                        .build();
        }



}


