package com.adesso.eaydemir.eth.tdp.integration.moralis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Transaction {
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("nonce")
    private String nonce;
    @JsonProperty("transaction_index")
    private String transactionIndex;
    @JsonProperty("from_address")
    private String fromAddress;
    @JsonProperty("to_address")
    private String toAddress;
    @JsonProperty("value")
    private String value;
    @JsonProperty("gas")
    private String gas;
    @JsonProperty("gas_price")
    private String gasPrice;
    @JsonProperty("input")
    private String input;
    @JsonProperty("receipt_cumulative_gas_used")
    private String receiptCumulativeGasUsed;
    @JsonProperty("receipt_gas_used")
    private String receiptGasUsed;
    @JsonProperty("receipt_contract_address")
    private String receiptContractAddress;
    @JsonProperty("receipt_root")
    private String receiptRoot;
    @JsonProperty("receipt_status")
    private String receiptStatus;
    @JsonProperty("block_timestamp")
    private String blockTimestamp;
    @JsonProperty("block_number")
    private String blockNumber;
    @JsonProperty("block_hash")
    private String blockHash;
}
