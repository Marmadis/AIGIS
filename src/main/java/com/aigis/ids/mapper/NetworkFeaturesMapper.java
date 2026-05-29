package com.aigis.ids.mapper;
import com.aigis.ids.dto.NetworkFeaturesDTO;
import com.aigis.ids.entity.AbuseInfo;
import com.aigis.ids.entity.FlowMeterLogZeek;
import com.aigis.ids.entity.VirusTotalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NetworkFeaturesMapper {

    default NetworkFeaturesDTO toNetworkFeaturesDTO(
            FlowMeterLogZeek zeek,
            String srcIp, String srcPort,
            String dstIp, String dstPort,
            AbuseInfo srcAbuse, VirusTotalInfo srcVt,
            AbuseInfo dstAbuse, VirusTotalInfo dstVt) {

        if (zeek == null) {
            return null;
        }

        NetworkFeaturesDTO dto = new NetworkFeaturesDTO();

        // Собираем source-блок
        dto.setSource(mapToEndpoint(zeek, srcIp, srcPort, dstPort, srcAbuse, srcVt));

        // Собираем destination-блок
        dto.setDestination(mapToEndpoint(zeek, dstIp, srcPort, dstPort, dstAbuse, dstVt));

        return dto;
    }

    @Mapping(target = "ipAddress", source = "ip")
    @Mapping(target = "sourcePort", source = "sPort")
    @Mapping(target = "destinationPort", source = "dPort")

    // Маппинг блока Threat Intelligence
    @Mapping(target = "abuseScore", source = "abuse.abuseConfidenceScore")
    @Mapping(target = "maliciousVotes", source = "vt.totalVotes.malicious")
    @Mapping(target = "totalReports", source = "abuse.totalReports")
    @Mapping(target = "isTor", source = "abuse.tor")

    // Переопределяем несовпадающие имена полей из Zeek-лога в DTO
    @Mapping(target = "totalLengthOfFwdPackets", source = "zeek.totalLengthFwdPackets")
    @Mapping(target = "totalLengthOfBwdPackets", source = "zeek.totalLengthBwdPackets")
    @Mapping(target = "fwdHeaderLength1", source = "zeek.fwdHeaderSizeTotal") // По вашему коду это размер заголовков
    @Mapping(target = "initWinBytesForward", source = "zeek.initWindowBytesForward")
    @Mapping(target = "initWinBytesBackward", source = "zeek.initWindowBytesBackward")
    NetworkFeaturesDTO.EndpointFeatures mapToEndpoint(
            FlowMeterLogZeek zeek,
            String ip, String sPort, String dPort,
            AbuseInfo abuse, VirusTotalInfo vt
    );
}
