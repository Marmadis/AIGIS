package com.aigis.ids.provider;

public interface ThreatProvider {
    int getScore(String ipAddress);
    String getProviderName();
}
