package com.aigis.ids.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(indexName = "ibmxforce_info_about_ip_address")
public class IBMXforceInfo {
    @Id
    private String id = UUID.randomUUID().toString();

    private String ipAddress;

    private String cats;

    private String subscore;

    private String history;

    private String linked_entities;
}
