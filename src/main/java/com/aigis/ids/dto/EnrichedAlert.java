package com.aigis.ids.dto;

public record EnrichedAlert(
        String alertId,
        String timestamp,
        String sourceIp,
        String description,
        AbuseResponse abuseInfo,        // Данные из AbuseIPDB
        VirustotalResponse vtInfo,      // Данные из VirusTotal
        IBMXForceResponse xforceInfo,   // Данные из IBM
        int finalRiskScore              // Ваш рассчитанный общий рейтинг
) {}
