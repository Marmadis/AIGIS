package com.aigis.ids.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ElastichConfig {

        @Value("${spring.elasticsearch.uris}")
        private String elasticUrl;

        @Bean
        public RestClient elasticClient() {
            return RestClient.builder()
                    .baseUrl(elasticUrl)
                    .defaultHeader("Content-Type", "application/json")
                    .build();
        }

}
