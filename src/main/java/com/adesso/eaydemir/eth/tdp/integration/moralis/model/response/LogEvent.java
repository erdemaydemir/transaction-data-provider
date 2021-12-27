package com.adesso.eaydemir.eth.tdp.integration.moralis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LogEvent {
    @JsonProperty("transaction_hash")
    private String transactionHash;
    @JsonProperty("address")
    private String address;
    @JsonProperty("block_timestamp")
    private String blockTimestamp;
    @JsonProperty("block_number")
    private String blockNumber;
    @JsonProperty("block_hash")
    private String blockHash;
    @JsonProperty("data")
    private Object data;
}
