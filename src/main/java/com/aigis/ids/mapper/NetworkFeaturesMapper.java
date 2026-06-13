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
        if (zeek == null) return null;

        NetworkFeaturesDTO dto = new NetworkFeaturesDTO();
        dto.setSource(mapToEndpoint(zeek, srcIp, srcPort, dstPort, srcAbuse, srcVt));
        dto.setDestination(mapToEndpoint(zeek, dstIp, dstPort, srcPort, dstAbuse, dstVt));
        return dto;
    }

    @Mapping(target = "ipAddress",    source = "ip")
    @Mapping(target = "sourcePort",   source = "sPort")
    @Mapping(target = "destinationPort", source = "dPort")

    // --- Threat Intel ---
    @Mapping(target = "abuseScore",     source = "abuse.abuseConfidenceScore")
    @Mapping(target = "maliciousVotes", source = "vt.totalVotes.malicious")
    @Mapping(target = "totalReports",   source = "abuse.totalReports")
    @Mapping(target = "isTor",          source = "abuse.tor")

    // --- Пакеты (Long → Double, имена не совпадают) ---
    @Mapping(target = "totalFwdPackets",      source = "zeek.totalFwdPackets")
    @Mapping(target = "totalBackwardPackets", source = "zeek.totalBwdPackets")

    // --- Длины пакетов (fwd) ---
    @Mapping(target = "totalLengthOfFwdPackets", source = "zeek.totalLengthFwdPackets")
    @Mapping(target = "fwdPacketLengthMax",      source = "zeek.fwdPacketLengthMax")
    @Mapping(target = "fwdPacketLengthMin",      source = "zeek.fwdPacketLengthMin")
    @Mapping(target = "fwdPacketLengthMean",     source = "zeek.fwdPacketLengthMean")
    @Mapping(target = "fwdPacketLengthStd",      source = "zeek.fwdPacketLengthStd")

    // --- Длины пакетов (bwd) ---
    @Mapping(target = "totalLengthOfBwdPackets", source = "zeek.totalLengthBwdPackets")
    @Mapping(target = "bwdPacketLengthMax",      source = "zeek.bwdPacketLengthMax")
    @Mapping(target = "bwdPacketLengthMin",      source = "zeek.bwdPacketLengthMin")
    @Mapping(target = "bwdPacketLengthMean",     source = "zeek.bwdPacketLengthMean")
    @Mapping(target = "bwdPacketLengthStd",      source = "zeek.bwdPacketLengthStd")

    // --- Flow payload (flow_pkts_payload → общие метрики пакетов) ---
    @Mapping(target = "minPacketLength",      source = "zeek.flowPacketLengthMin")
    @Mapping(target = "maxPacketLength",      source = "zeek.flowPacketLengthMax")
    @Mapping(target = "packetLengthMean",     source = "zeek.flowPacketLengthMean")
    @Mapping(target = "packetLengthStd",      source = "zeek.flowPacketLengthStd")
    // packetLengthVariance — нет в Zeek, MapStruct оставит null (нормально)

    // --- Flow IAT ---
    @Mapping(target = "flowIatMean", source = "zeek.flowIatMean")
    @Mapping(target = "flowIatStd",  source = "zeek.flowIatStd")
    @Mapping(target = "flowIatMax",  source = "zeek.flowIatMax")
    @Mapping(target = "flowIatMin",  source = "zeek.flowIatMin")

    // --- Fwd IAT ---
    @Mapping(target = "fwdIatTotal", source = "zeek.fwdIatTotal")
    @Mapping(target = "fwdIatMean",  source = "zeek.fwdIatMean")
    @Mapping(target = "fwdIatStd",   source = "zeek.fwdIatStd")
    @Mapping(target = "fwdIatMax",   source = "zeek.fwdIatMax")
    @Mapping(target = "fwdIatMin",   source = "zeek.fwdIatMin")

    // --- Bwd IAT ---
    @Mapping(target = "bwdIatTotal", source = "zeek.bwdIatTotal")
    @Mapping(target = "bwdIatMean",  source = "zeek.bwdIatMean")
    @Mapping(target = "bwdIatStd",   source = "zeek.bwdIatStd")
    @Mapping(target = "bwdIatMax",   source = "zeek.bwdIatMax")
    @Mapping(target = "bwdIatMin",   source = "zeek.bwdIatMin")

    // --- PSH / URG флаги ---
    @Mapping(target = "fwdPshFlags", source = "zeek.fwdPshFlags")
    @Mapping(target = "bwdPshFlags", source = "zeek.bwdPshFlags")
    @Mapping(target = "fwdUrgFlags", source = "zeek.fwdUrgFlags")
    @Mapping(target = "bwdUrgFlags", source = "zeek.bwdUrgFlags")

    // --- Заголовки ---
    @Mapping(target = "fwdHeaderLength",  source = "zeek.fwdHeaderSizeTotal")
    @Mapping(target = "bwdHeaderLength",  source = "zeek.bwdHeaderSizeTotal")
    @Mapping(target = "fwdHeaderLength1", source = "zeek.fwdHeaderSizeTotal")

    // --- Packets/s ---
    @Mapping(target = "fwdPacketsPerSecond", source = "zeek.fwdPacketsPerSec")
    @Mapping(target = "bwdPacketsPerSecond", source = "zeek.bwdPacketsPerSec")

    // --- Flow-уровневые метрики ---
    @Mapping(target = "flowBytesPerSecond",   source = "zeek.flowBytesPerSecond")
    @Mapping(target = "flowPacketsPerSecond", source = "zeek.flowPacketsPerSec")
    @Mapping(target = "downUpRatio",          source = "zeek.downUpRatio")

    // --- Размер пакета (avg из flow payload) ---
    @Mapping(target = "averagePacketSize",  source = "zeek.flowPacketLengthMean")
    @Mapping(target = "avgFwdSegmentSize",  source = "zeek.fwdPacketLengthMean")
    @Mapping(target = "avgBwdSegmentSize",  source = "zeek.bwdPacketLengthMean")

    // --- Flag counts (flow-level) ---
    @Mapping(target = "finFlagCount", source = "zeek.finFlagCount")
    @Mapping(target = "synFlagCount", source = "zeek.synFlagCount")
    @Mapping(target = "rstFlagCount", source = "zeek.rstFlagCount")
    @Mapping(target = "ackFlagCount", source = "zeek.ackFlagCount")
    @Mapping(target = "cweFlagCount", source = "zeek.cwrFlagCount")  // CWR→CWE
    @Mapping(target = "eceFlagCount", source = "zeek.eceFlagCount")
    // pshFlagCount, urgFlagCount — нет отдельного flow-level поля в Zeek

    // --- Bulk ---
    @Mapping(target = "fwdAvgBytesBulk",   source = "zeek.fwdBulkBytes")
    @Mapping(target = "fwdAvgPacketsBulk", source = "zeek.fwdBulkPackets")
    @Mapping(target = "fwdAvgBulkRate",    source = "zeek.fwdBulkRate")
    @Mapping(target = "bwdAvgBytesBulk",   source = "zeek.bwdBulkBytes")
    @Mapping(target = "bwdAvgPacketsBulk", source = "zeek.bwdBulkPackets")
    @Mapping(target = "bwdAvgBulkRate",    source = "zeek.bwdBulkRate")

    // --- Subflow ---
    @Mapping(target = "subflowFwdPackets", source = "zeek.subflowFwdPackets")
    @Mapping(target = "subflowFwdBytes",   source = "zeek.subflowFwdBytes")
    @Mapping(target = "subflowBwdPackets", source = "zeek.subflowBwdPackets")
    @Mapping(target = "subflowBwdBytes",   source = "zeek.subflowBwdBytes")

    // --- Init window ---
    @Mapping(target = "initWinBytesForward",  source = "zeek.initWindowBytesForward")
    @Mapping(target = "initWinBytesBackward", source = "zeek.initWindowBytesBackward")

    // --- Active / Idle ---
    @Mapping(target = "activeMean", source = "zeek.activeMean")
    @Mapping(target = "activeStd",  source = "zeek.activeStd")
    @Mapping(target = "activeMax",  source = "zeek.activeMax")
    @Mapping(target = "activeMin",  source = "zeek.activeMin")
    @Mapping(target = "idleMean",   source = "zeek.idleMean")
    @Mapping(target = "idleStd",    source = "zeek.idleStd")
    @Mapping(target = "idleMax",    source = "zeek.idleMax")
    @Mapping(target = "idleMin",    source = "zeek.idleMin")

    // --- Поля которых нет в Zeek — явно игнорируем ---
    @Mapping(target = "packetLengthVariance", ignore = true)
    @Mapping(target = "pshFlagCount",         ignore = true)
    @Mapping(target = "urgFlagCount",         ignore = true)
    @Mapping(target = "actDataPktFwd",        ignore = true)
    @Mapping(target = "minSegSizeForward",    ignore = true)

    @Mapping(target = "flowDuration", source = "zeek.flowDuration")
    NetworkFeaturesDTO.EndpointFeatures mapToEndpoint(
            FlowMeterLogZeek zeek,
            String ip, String sPort, String dPort,
            AbuseInfo abuse, VirusTotalInfo vt
    );
}