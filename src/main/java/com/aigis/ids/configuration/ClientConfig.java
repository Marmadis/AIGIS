package com.aigis.ids.configuration;

import com.aigis.ids.service.APIKeyManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

        private  final APIKeyManagerService apiKeyManagerService;

        public ClientConfig(APIKeyManagerService  apiKeyManagerService) {
                this.apiKeyManagerService = apiKeyManagerService;
        }

        @Bean
        public RestClient abuseIpDbClient() {
            return RestClient.builder()
                    .baseUrl("https://api.abuseipdb.com/api/v2")
                    .requestInterceptor((request, body, execution) -> {
                            request.getHeaders().add("Key", apiKeyManagerService.getAbuseKey());
                            request.getHeaders().add("Accept", "application/json");
                            return execution.execute(request, body);
                    })
                    .build();
        }

        @Bean
        public RestClient virusTotalClient(){
                return RestClient.builder()
                        .baseUrl("https://www.virustotal.com/api/v3")
                        .requestInterceptor(((request, body, execution) -> {
                                request.getHeaders().add("Key",apiKeyManagerService.getVirusTotalKey());
                                request.getHeaders().add("Accept", "application/json");
                                return execution.execute(request,body);
                        }))
                        .build();
        }

        @Bean
        public RestClient ibmxforceClient(){
                return RestClient.builder()
                        .baseUrl("https://api.xforce.ibmcloud.com/ipr/")
                        .requestInterceptor((request, body, execution) -> {
                                request.getHeaders().add("Key",apiKeyManagerService.getIBMXForceKey());
                                request.getHeaders().add("Accept", "application/json");
                                return execution.execute(request,body);
                        })
                        .build();
        }



}


