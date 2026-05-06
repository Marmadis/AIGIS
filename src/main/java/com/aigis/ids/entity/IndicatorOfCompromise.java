package com.aigis.ids.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(indexName = "indicator_of_compromise",createIndex = false)
public class IndicatorOfCompromise {
    @Id
    private String id = UUID.randomUUID().toString();

    private String alertId;

    private String ipAddress;

    private String message;



}
