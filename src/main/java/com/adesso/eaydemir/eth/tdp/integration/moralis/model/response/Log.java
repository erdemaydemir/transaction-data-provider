package com.adesso.eaydemir.eth.tdp.integration.moralis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Log {
    @JsonProperty("log_index")
    private String logIndex;
    @JsonProperty("transaction_hash")
    private String transactionHash;
    @JsonProperty("transaction_index")
    private String transactionIndex;
    @JsonProperty("address")
    private String address;
    @JsonProperty("data")
    private String data;
    @JsonProperty("topic0")
    private String topic0;
    @JsonProperty("topic1")
    private String topic1;
    @JsonProperty("topic2")
    private String topic2;
    @JsonProperty("topic3")
    private String topic3;
    @JsonProperty("block_timestamp")
    private String blockTimestamp;
    @JsonProperty("block_number")
    private String blockNumber;
    @JsonProperty("block_hash")
    private String blockHash;
}
