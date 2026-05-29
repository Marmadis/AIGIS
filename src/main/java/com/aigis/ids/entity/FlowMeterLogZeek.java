package com.aigis.ids.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "zeek-flowmeter",createIndex = false)
public class FlowMeterLogZeek {

    @Id
    private String id;

    @JsonProperty("@timestamp")
    private String timestamp;

    private String uid;

    @JsonProperty("flow_duration")
    private Double flowDuration;

    @JsonProperty("fwd_pkts_tot")
    private Long totalFwdPackets;

    @JsonProperty("bwd_pkts_tot")
    private Long totalBwdPackets;

    @JsonProperty("fwd_data_pkts_tot")
    private Long fwdDataPacketsTotal;

    @JsonProperty("bwd_data_pkts_tot")
    private Long bwdDataPacketsTotal;

    @JsonProperty("fwd_pkts_per_sec")
    private Double fwdPacketsPerSec;

    @JsonProperty("bwd_pkts_per_sec")
    private Double bwdPacketsPerSec;

    @JsonProperty("flow_pkts_per_sec")
    private Double flowPacketsPerSec;

    @JsonProperty("down_up_ratio")
    private Double downUpRatio;

    @JsonProperty("fwd_header_size_tot")
    private Long fwdHeaderSizeTotal;

    @JsonProperty("bwd_header_size_tot")
    private Long bwdHeaderSizeTotal;

    @JsonProperty("flow_FIN_flag_count")
    private Integer finFlagCount;

    @JsonProperty("flow_SYN_flag_count")
    private Integer synFlagCount;

    @JsonProperty("flow_RST_flag_count")
    private Integer rstFlagCount;

    @JsonProperty("flow_ACK_flag_count")
    private Integer ackFlagCount;

    @JsonProperty("flow_CWR_flag_count")
    private Integer cwrFlagCount;

    @JsonProperty("flow_ECE_flag_count")
    private Integer eceFlagCount;

    @JsonProperty("fwd_PSH_flag_count")
    private Integer fwdPshFlags;

    @JsonProperty("bwd_PSH_flag_count")
    private Integer bwdPshFlags;

    @JsonProperty("fwd_URG_flag_count")
    private Integer fwdUrgFlags;

    @JsonProperty("bwd_URG_flag_count")
    private Integer bwdUrgFlags;

    @JsonProperty("fwd_pkts_payload.min")
    private Double fwdPacketLengthMin;

    @JsonProperty("fwd_pkts_payload.max")
    private Double fwdPacketLengthMax;

    @JsonProperty("fwd_pkts_payload.avg")
    private Double fwdPacketLengthMean;

    @JsonProperty("fwd_pkts_payload.std")
    private Double fwdPacketLengthStd;

    @JsonProperty("fwd_pkts_payload.tot")
    private Double totalLengthFwdPackets;

    @JsonProperty("bwd_pkts_payload.min")
    private Double bwdPacketLengthMin;

    @JsonProperty("bwd_pkts_payload.max")
    private Double bwdPacketLengthMax;

    @JsonProperty("bwd_pkts_payload.avg")
    private Double bwdPacketLengthMean;

    @JsonProperty("bwd_pkts_payload.std")
    private Double bwdPacketLengthStd;

    @JsonProperty("bwd_pkts_payload.tot")
    private Double totalLengthBwdPackets;

    @JsonProperty("flow_pkts_payload.min")
    private Double flowPacketLengthMin;

    @JsonProperty("flow_pkts_payload.max")
    private Double flowPacketLengthMax;

    @JsonProperty("flow_pkts_payload.avg")
    private Double flowPacketLengthMean;

    @JsonProperty("flow_pkts_payload.std")
    private Double flowPacketLengthStd;

    @JsonProperty("flow_pkts_payload.tot")
    private Double flowPacketLengthTotal;

    @JsonProperty("flow_iat.min")
    private Double flowIatMin;

    @JsonProperty("flow_iat.max")
    private Double flowIatMax;

    @JsonProperty("flow_iat.avg")
    private Double flowIatMean;

    @JsonProperty("flow_iat.std")
    private Double flowIatStd;

    @JsonProperty("flow_iat.tot")
    private Double flowIatTotal;

    @JsonProperty("fwd_iat.min")
    private Double fwdIatMin;

    @JsonProperty("fwd_iat.max")
    private Double fwdIatMax;

    @JsonProperty("fwd_iat.avg")
    private Double fwdIatMean;

    @JsonProperty("fwd_iat.std")
    private Double fwdIatStd;

    @JsonProperty("fwd_iat.tot")
    private Double fwdIatTotal;

    @JsonProperty("bwd_iat.min")
    private Double bwdIatMin;

    @JsonProperty("bwd_iat.max")
    private Double bwdIatMax;

    @JsonProperty("bwd_iat.avg")
    private Double bwdIatMean;

    @JsonProperty("bwd_iat.std")
    private Double bwdIatStd;

    @JsonProperty("bwd_iat.tot")
    private Double bwdIatTotal;

    @JsonProperty("payload_bytes_per_second")
    private Double flowBytesPerSecond;

    @JsonProperty("fwd_subflow_pkts")
    private Double subflowFwdPackets;

    @JsonProperty("bwd_subflow_pkts")
    private Double subflowBwdPackets;

    @JsonProperty("fwd_subflow_bytes")
    private Double subflowFwdBytes;

    @JsonProperty("bwd_subflow_bytes")
    private Double subflowBwdBytes;

    @JsonProperty("fwd_bulk_bytes")
    private Double fwdBulkBytes;

    @JsonProperty("bwd_bulk_bytes")
    private Double bwdBulkBytes;

    @JsonProperty("fwd_bulk_packets")
    private Double fwdBulkPackets;

    @JsonProperty("bwd_bulk_packets")
    private Double bwdBulkPackets;

    @JsonProperty("fwd_bulk_rate")
    private Double fwdBulkRate;

    @JsonProperty("bwd_bulk_rate")
    private Double bwdBulkRate;

    @JsonProperty("active.min")
    private Double activeMin;

    @JsonProperty("active.max")
    private Double activeMax;

    @JsonProperty("active.avg")
    private Double activeMean;

    @JsonProperty("active.std")
    private Double activeStd;

    @JsonProperty("active.tot")
    private Double activeTotal;

    @JsonProperty("idle.min")
    private Double idleMin;

    @JsonProperty("idle.max")
    private Double idleMax;

    @JsonProperty("idle.avg")
    private Double idleMean;

    @JsonProperty("idle.std")
    private Double idleStd;

    @JsonProperty("idle.tot")
    private Double idleTotal;

    @JsonProperty("fwd_init_window_size")
    private Integer initWindowBytesForward;

    @JsonProperty("bwd_init_window_size")
    private Integer initWindowBytesBackward;

    @JsonProperty("fwd_last_window_size")
    private Integer fwdLastWindowSize;

    @JsonProperty("bwd_last_window_size")
    private Integer bwdLastWindowSize;
}
