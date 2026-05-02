package com.aigis.ids.service;

import com.aigis.ids.dto.AbuseResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Service
public class AbuseipdbService {

        private final RestClient abuseClient;

        public AbuseipdbService(RestClient abuseClient) {
            this.abuseClient = abuseClient;
        }

        public AbuseResponse checkIp(String ip) {
            try {
                return abuseClient.get()
                        .uri(uriBuilder -> uriBuilder.path("/check").queryParam("ipAddress", ip).build())
                        .retrieve()
                        // 1. Ловим ошибки клиента (4xx)
                        .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                            throw new RuntimeException("Ошибка клиента: " + response.getStatusCode() +
                                    ". Проверьте правильность ключа или лимиты.");
                        })
                        // 2. Ловим ошибки сервера (5xx)
                        .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                            throw new RuntimeException("Сервер AbuseIPDB недоступен. Код: " + response.getStatusCode());
                        })
                        .body(AbuseResponse.class);
            } catch (ResourceAccessException e) {
                // 3. Ловим ошибки сети (сервис упал, тайм-аут, нет интернета)
                throw new RuntimeException("Не удалось связаться с сервером: сетевая ошибка");
            } catch (Exception e) {
                // 4. Любые другие непредвиденные ошибки
                throw new RuntimeException("Произошла ошибка при выполнении запроса: " + e.getMessage());
            }
        }

}
