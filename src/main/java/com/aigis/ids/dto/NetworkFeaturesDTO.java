package com.aigis.ids.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkFeaturesDTO {

    @JsonProperty("source")
    private EndpointFeatures source;

    @JsonProperty("destination")
    private EndpointFeatures destination;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EndpointFeatures {

        // ==========================================
        // БАЗОВЫЕ ИДЕНТИФИКАТОРЫ (Из RawAlert / API)
        // ==========================================

        @JsonProperty("ip_address")
        private String ipAddress;

        @JsonProperty("Source Port")
        private String sourcePort;

        // ==========================================
        // СПИСОК ИЗ 82 ПРИЗНАКОВ (Порядковые номера 1-82)
        // ==========================================

        @JsonProperty("Destination Port")
        private String destinationPort; // 1

        @JsonProperty("Flow Duration")
        private Double flowDuration; // 2

        @JsonProperty("Total Fwd Packets")
        private Double totalFwdPackets; // 3

        @JsonProperty("Total Backward Packets")
        private Double totalBackwardPackets; // 4

        @JsonProperty("Total Length of Fwd Packets")
        private Double totalLengthOfFwdPackets; // 5

        @JsonProperty("Total Length of Bwd Packets")
        private Double totalLengthOfBwdPackets; // 6

        @JsonProperty("Fwd Packet Length Max")
        private Double fwdPacketLengthMax; // 7

        @JsonProperty("Fwd Packet Length Min")
        private Double fwdPacketLengthMin; // 8

        @JsonProperty("Fwd Packet Length Mean")
        private Double fwdPacketLengthMean; // 9

        @JsonProperty("Fwd Packet Length Std")
        private Double fwdPacketLengthStd; // 10

        @JsonProperty("Bwd Packet Length Max")
        private Double bwdPacketLengthMax; // 11

        @JsonProperty("Bwd Packet Length Min")
        private Double bwdPacketLengthMin; // 12

        @JsonProperty("Bwd Packet Length Mean")
        private Double bwdPacketLengthMean; // 13

        @JsonProperty("Bwd Packet Length Std")
        private Double bwdPacketLengthStd; // 14

        @JsonProperty("Flow Bytes/s")
        private Double flowBytesPerSecond; // 15

        @JsonProperty("Flow Packets/s")
        private Double flowPacketsPerSecond; // 16

        @JsonProperty("Flow IAT Mean")
        private Double flowIatMean; // 17

        @JsonProperty("Flow IAT Std")
        private Double flowIatStd; // 18

        @JsonProperty("Flow IAT Max")
        private Double flowIatMax; // 19

        @JsonProperty("Flow IAT Min")
        private Double flowIatMin; // 20

        @JsonProperty("Fwd IAT Total")
        private Double fwdIatTotal; // 21

        @JsonProperty("Fwd IAT Mean")
        private Double fwdIatMean; // 22

        @JsonProperty("Fwd IAT Std")
        private Double fwdIatStd; // 23

        @JsonProperty("Fwd IAT Max")
        private Double fwdIatMax; // 24

        @JsonProperty("Fwd IAT Min")
        private Double fwdIatMin; // 25

        @JsonProperty("Bwd IAT Total")
        private Double bwdIatTotal; // 26

        @JsonProperty("Bwd IAT Mean")
        private Double bwdIatMean; // 27

        @JsonProperty("Bwd IAT Std")
        private Double bwdIatStd; // 28

        @JsonProperty("Bwd IAT Max")
        private Double bwdIatMax; // 29

        @JsonProperty("Bwd IAT Min")
        private Double bwdIatMin; // 30

        @JsonProperty("Fwd PSH Flags")
        private Double fwdPshFlags; // 31

        @JsonProperty("Bwd PSH Flags")
        private Double bwdPshFlags; // 32

        @JsonProperty("Fwd URG Flags")
        private Double fwdUrgFlags; // 33

        @JsonProperty("Bwd URG Flags")
        private Double bwdUrgFlags; // 34

        @JsonProperty("Fwd Header Length")
        private Double fwdHeaderLength; // 35

        @JsonProperty("Bwd Header Length")
        private Double bwdHeaderLength; // 36

        @JsonProperty("Fwd Packets/s")
        private Double fwdPacketsPerSecond; // 37

        @JsonProperty("Bwd Packets/s")
        private Double bwdPacketsPerSecond; // 38

        @JsonProperty("Min Packet Length")
        private Double minPacketLength; // 39

        @JsonProperty("Max Packet Length")
        private Double maxPacketLength; // 40

        @JsonProperty("Packet Length Mean")
        private Double packetLengthMean; // 41

        @JsonProperty("Packet Length Std")
        private Double packetLengthStd; // 42

        @JsonProperty("Packet Length Variance")
        private Double packetLengthVariance; // 43

        @JsonProperty("FIN Flag Count")
        private Double finFlagCount; // 44

        @JsonProperty("SYN Flag Count")
        private Double synFlagCount; // 45

        @JsonProperty("RST Flag Count")
        private Double rstFlagCount; // 46

        @JsonProperty("PSH Flag Count")
        private Double pshFlagCount; // 47

        @JsonProperty("ACK Flag Count")
        private Double ackFlagCount; // 48

        @JsonProperty("URG Flag Count")
        private Double urgFlagCount; // 49

        @JsonProperty("CWE Flag Count")
        private Double cweFlagCount; // 50

        @JsonProperty("ECE Flag Count")
        private Double eceFlagCount; // 51

        @JsonProperty("Down/Up Ratio")
        private Double downUpRatio; // 52

        @JsonProperty("Average Packet Size")
        private Double averagePacketSize; // 53

        @JsonProperty("Avg Fwd Segment Size")
        private Double avgFwdSegmentSize; // 54

        @JsonProperty("Avg Bwd Segment Size")
        private Double avgBwdSegmentSize; // 55

        @JsonProperty("Fwd Header Length.1")
        private Double fwdHeaderLength1; // 56

        @JsonProperty("Fwd Avg Bytes/Bulk")
        private Double fwdAvgBytesBulk; // 57

        @JsonProperty("Fwd Avg Packets/Bulk")
        private Double fwdAvgPacketsBulk; // 58

        @JsonProperty("Fwd Avg Bulk Rate")
        private Double fwdAvgBulkRate; // 59

        @JsonProperty("Bwd Avg Bytes/Bulk")
        private Double bwdAvgBytesBulk; // 60

        @JsonProperty("Bwd Avg Packets/Bulk")
        private Double bwdAvgPacketsBulk; // 61

        @JsonProperty("Bwd Avg Bulk Rate")
        private Double bwdAvgBulkRate; // 62

        @JsonProperty("Subflow Fwd Packets")
        private Double subflowFwdPackets; // 63

        @JsonProperty("Subflow Fwd Bytes")
        private Double subflowFwdBytes; // 64

        @JsonProperty("Subflow Bwd Packets")
        private Double subflowBwdPackets; // 65

        @JsonProperty("Subflow Bwd Bytes")
        private Double subflowBwdBytes; // 66

        @JsonProperty("Init_Win_bytes_forward")
        private Double initWinBytesForward; // 67

        @JsonProperty("Init_Win_bytes_backward")
        private Double initWinBytesBackward; // 68

        @JsonProperty("act_data_pkt_fwd")
        private Double actDataPktFwd; // 69

        @JsonProperty("min_seg_size_forward")
        private Double minSegSizeForward; // 70

        @JsonProperty("Active Mean")
        private Double activeMean; // 71

        @JsonProperty("Active Std")
        private Double activeStd; // 72

        @JsonProperty("Active Max")
        private Double activeMax; // 73

        @JsonProperty("Active Min")
        private Double activeMin; // 74

        @JsonProperty("Idle Mean")
        private Double idleMean; // 75

        @JsonProperty("Idle Std")
        private Double idleStd; // 76

        @JsonProperty("Idle Max")
        private Double idleMax; // 77

        @JsonProperty("Idle Min")
        private Double idleMin; // 78

        // ==========================================
        // БЛОК THREAT INTEL (Из AbuseInfo и VirusTotalInfo)
        // ==========================================

        @JsonProperty("abuseScore")
        private Integer abuseScore; // 79

        @JsonProperty("malicious_votes")
        private Integer maliciousVotes; // 80

        @JsonProperty("totalReports")
        private Integer totalReports; // 81

        @JsonProperty("isTor")
        private Boolean isTor; // 82
    }
}