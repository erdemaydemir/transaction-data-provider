package com.adesso.eaydemir.eth.tdp.document;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Ethereum Transaction Document Object
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "txs")
public class Tx {

    @Id
    @JsonAlias("hash")
    private String hash;

    @Field(type = FieldType.Integer)
    @JsonAlias("nonce")
    private Integer nonce;

    @Field(type = FieldType.Integer)
    @JsonAlias("transaction_index")
    private Integer transactionIndex;

    @Field(type = FieldType.Keyword)
    @JsonAlias("from_address")
    private String fromAddress;

    @Field(type = FieldType.Keyword)
    @JsonAlias("to_address")
    private String toAddress;

    @Field(type = FieldType.Long)
    @JsonAlias("value")
    private BigDecimal value;

    @Field(type = FieldType.Long)
    @JsonAlias("gas")
    private BigDecimal gas;

    @Field(type = FieldType.Long)
    @JsonAlias("gas_price")
    private BigDecimal gasPrice;

    @Field(type = FieldType.Text)
    @JsonAlias("input")
    private String input;

    @Field(type = FieldType.Long)
    @JsonAlias("receipt_cumulative_gas_used")
    private BigDecimal receiptCumulativeGasUsed;

    @Field(type = FieldType.Long)
    @JsonAlias("receipt_gas_used")
    private BigDecimal receiptGasUsed;

    @Field(type = FieldType.Keyword)
    @JsonAlias("receipt_contract_address")
    private String receiptContractAddress;

    @Field(type = FieldType.Text)
    @JsonAlias("receipt_root")
    private String receiptRoot;

    @Field(type = FieldType.Integer)
    @JsonAlias("receipt_status")
    private Integer receiptStatus;

    @Field(type = FieldType.Date)
    @JsonAlias("block_timestamp")
    private Date blockTimestamp;

    @Field(type = FieldType.Long)
    @JsonAlias("block_number")
    private Long blockNumber;

    @Field(type = FieldType.Keyword)
    @JsonAlias("block_hash")
    private String blockHash;
}
