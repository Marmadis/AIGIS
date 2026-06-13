package com.aigis.ids.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "zeek-flowmeter", createIndex = false)
public class FlowMeterLogZeek {

    @Id
    private String id;

    private String uid;

    @Field(name = "flow_duration", type = FieldType.Double)
    private Double flowDuration;

    @Field(name = "fwd_pkts_tot", type = FieldType.Long)
    private Long totalFwdPackets;

    @Field(name = "bwd_pkts_tot", type = FieldType.Long)
    private Long totalBwdPackets;

    @Field(name = "fwd_data_pkts_tot", type = FieldType.Long)
    private Long fwdDataPacketsTotal;

    @Field(name = "bwd_data_pkts_tot", type = FieldType.Long)
    private Long bwdDataPacketsTotal;

    @Field(name = "fwd_pkts_per_sec", type = FieldType.Double)
    private Double fwdPacketsPerSec;

    @Field(name = "bwd_pkts_per_sec", type = FieldType.Double)
    private Double bwdPacketsPerSec;

    @Field(name = "flow_pkts_per_sec", type = FieldType.Double)
    private Double flowPacketsPerSec;

    @Field(name = "payload_bytes_per_second", type = FieldType.Double)
    private Double flowBytesPerSecond;

    @Field(name = "down_up_ratio", type = FieldType.Double)
    private Double downUpRatio;

    @Field(name = "fwd_header_size_tot", type = FieldType.Long)
    private Long fwdHeaderSizeTotal;

    @Field(name = "bwd_header_size_tot", type = FieldType.Long)
    private Long bwdHeaderSizeTotal;

    // Flag counts
    @Field(name = "flow_FIN_flag_count", type = FieldType.Integer)
    private Integer finFlagCount;

    @Field(name = "flow_SYN_flag_count", type = FieldType.Integer)
    private Integer synFlagCount;

    @Field(name = "flow_RST_flag_count", type = FieldType.Integer)
    private Integer rstFlagCount;

    @Field(name = "flow_ACK_flag_count", type = FieldType.Integer)
    private Integer ackFlagCount;

    @Field(name = "flow_CWR_flag_count", type = FieldType.Integer)
    private Integer cwrFlagCount;

    @Field(name = "flow_ECE_flag_count", type = FieldType.Integer)
    private Integer eceFlagCount;

    @Field(name = "fwd_PSH_flag_count", type = FieldType.Integer)
    private Integer fwdPshFlags;

    @Field(name = "bwd_PSH_flag_count", type = FieldType.Integer)
    private Integer bwdPshFlags;

    @Field(name = "fwd_URG_flag_count", type = FieldType.Integer)
    private Integer fwdUrgFlags;

    @Field(name = "bwd_URG_flag_count", type = FieldType.Integer)
    private Integer bwdUrgFlags;

    // fwd payload — ТОЧКИ
    @Field(name = "fwd_pkts_payload.min", type = FieldType.Double)
    private Double fwdPacketLengthMin;

    @Field(name = "fwd_pkts_payload.max", type = FieldType.Double)
    private Double fwdPacketLengthMax;

    @Field(name = "fwd_pkts_payload.avg", type = FieldType.Double)
    private Double fwdPacketLengthMean;

    @Field(name = "fwd_pkts_payload.std", type = FieldType.Double)
    private Double fwdPacketLengthStd;

    @Field(name = "fwd_pkts_payload.tot", type = FieldType.Double)
    private Double totalLengthFwdPackets;

    // bwd payload — ТОЧКИ
    @Field(name = "bwd_pkts_payload.min", type = FieldType.Double)
    private Double bwdPacketLengthMin;

    @Field(name = "bwd_pkts_payload.max", type = FieldType.Double)
    private Double bwdPacketLengthMax;

    @Field(name = "bwd_pkts_payload.avg", type = FieldType.Double)
    private Double bwdPacketLengthMean;

    @Field(name = "bwd_pkts_payload.std", type = FieldType.Double)
    private Double bwdPacketLengthStd;

    @Field(name = "bwd_pkts_payload.tot", type = FieldType.Double)
    private Double totalLengthBwdPackets;

    // flow payload — ТОЧКИ
    @Field(name = "flow_pkts_payload.min", type = FieldType.Double)
    private Double flowPacketLengthMin;

    @Field(name = "flow_pkts_payload.max", type = FieldType.Double)
    private Double flowPacketLengthMax;

    @Field(name = "flow_pkts_payload.avg", type = FieldType.Double)
    private Double flowPacketLengthMean;

    @Field(name = "flow_pkts_payload.std", type = FieldType.Double)
    private Double flowPacketLengthStd;

    @Field(name = "flow_pkts_payload.tot", type = FieldType.Double)
    private Double flowPacketLengthTotal;

    // flow IAT — ТОЧКИ
    @Field(name = "flow_iat.min", type = FieldType.Double)
    private Double flowIatMin;

    @Field(name = "flow_iat.max", type = FieldType.Double)
    private Double flowIatMax;

    @Field(name = "flow_iat.avg", type = FieldType.Double)
    private Double flowIatMean;

    @Field(name = "flow_iat.std", type = FieldType.Double)
    private Double flowIatStd;

    @Field(name = "flow_iat.tot", type = FieldType.Double)
    private Double flowIatTotal;

    // fwd IAT — ТОЧКИ
    @Field(name = "fwd_iat.min", type = FieldType.Double)
    private Double fwdIatMin;

    @Field(name = "fwd_iat.max", type = FieldType.Double)
    private Double fwdIatMax;

    @Field(name = "fwd_iat.avg", type = FieldType.Double)
    private Double fwdIatMean;

    @Field(name = "fwd_iat.std", type = FieldType.Double)
    private Double fwdIatStd;

    @Field(name = "fwd_iat.tot", type = FieldType.Double)
    private Double fwdIatTotal;

    // bwd IAT — ТОЧКИ
    @Field(name = "bwd_iat.min", type = FieldType.Double)
    private Double bwdIatMin;

    @Field(name = "bwd_iat.max", type = FieldType.Double)
    private Double bwdIatMax;

    @Field(name = "bwd_iat.avg", type = FieldType.Double)
    private Double bwdIatMean;

    @Field(name = "bwd_iat.std", type = FieldType.Double)
    private Double bwdIatStd;

    @Field(name = "bwd_iat.tot", type = FieldType.Double)
    private Double bwdIatTotal;

    // active — ТОЧКИ
    @Field(name = "active.min", type = FieldType.Double)
    private Double activeMin;

    @Field(name = "active.max", type = FieldType.Double)
    private Double activeMax;

    @Field(name = "active.avg", type = FieldType.Double)
    private Double activeMean;

    @Field(name = "active.std", type = FieldType.Double)
    private Double activeStd;

    @Field(name = "active.tot", type = FieldType.Double)
    private Double activeTotal;

    // idle — ТОЧКИ
    @Field(name = "idle.min", type = FieldType.Double)
    private Double idleMin;

    @Field(name = "idle.max", type = FieldType.Double)
    private Double idleMax;

    @Field(name = "idle.avg", type = FieldType.Double)
    private Double idleMean;

    @Field(name = "idle.std", type = FieldType.Double)
    private Double idleStd;

    @Field(name = "idle.tot", type = FieldType.Double)
    private Double idleTotal;

    // Subflow
    @Field(name = "fwd_subflow_pkts", type = FieldType.Double)
    private Double subflowFwdPackets;

    @Field(name = "fwd_subflow_bytes", type = FieldType.Double)
    private Double subflowFwdBytes;

    @Field(name = "bwd_subflow_pkts", type = FieldType.Double)
    private Double subflowBwdPackets;

    @Field(name = "bwd_subflow_bytes", type = FieldType.Double)
    private Double subflowBwdBytes;

    // Bulk
    @Field(name = "fwd_bulk_bytes", type = FieldType.Double)
    private Double fwdBulkBytes;

    @Field(name = "bwd_bulk_bytes", type = FieldType.Double)
    private Double bwdBulkBytes;

    @Field(name = "fwd_bulk_packets", type = FieldType.Double)
    private Double fwdBulkPackets;

    @Field(name = "bwd_bulk_packets", type = FieldType.Double)
    private Double bwdBulkPackets;

    @Field(name = "fwd_bulk_rate", type = FieldType.Double)
    private Double fwdBulkRate;

    @Field(name = "bwd_bulk_rate", type = FieldType.Double)
    private Double bwdBulkRate;

    // Init window
    @Field(name = "fwd_init_window_size", type = FieldType.Integer)
    private Integer initWindowBytesForward;

    @Field(name = "bwd_init_window_size", type = FieldType.Integer)
    private Integer initWindowBytesBackward;

    @Field(name = "fwd_last_window_size", type = FieldType.Integer)
    private Integer fwdLastWindowSize;

    @Field(name = "bwd_last_window_size", type = FieldType.Integer)
    private Integer bwdLastWindowSize;
}