package com.aigis.ids.configuration;


import com.aigis.ids.service.APIKeyManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestClient;

@EnableAsync
@Configuration
@RequiredArgsConstructor
public class ClientConfig {

        private  final APIKeyManagerService apiKeyManagerService;



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
                                request.getHeaders().add("x-apikey",apiKeyManagerService.getVirusTotalKey());
                                request.getHeaders().add("Accept", "application/json");
                                return execution.execute(request,body);
                        }))
                        .build();
        }



    @Bean
    public RestClient mlClient() {
        return RestClient.builder()
                .baseUrl("http://ml-ai-service:5000") // Лучше оставить только хост
                .build();
    }

}


