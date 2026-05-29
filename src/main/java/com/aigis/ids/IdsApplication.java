package com.aigis.ids;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class IdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdsApplication.class, args);

	log.warn("Не забудьте добавить ключи  API для сервисов AbuseIPDB & VirusTotal");
	log.warn("Перед запуском aigis-backend необходимо поднять ElasticSearch для избежания ошибок");
	}


}
