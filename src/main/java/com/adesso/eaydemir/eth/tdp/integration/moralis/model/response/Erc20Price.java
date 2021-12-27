package com.adesso.eaydemir.eth.tdp.integration.moralis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Erc20Price {
    @JsonProperty("nativePrice")
    private NativeErc20Price nativePrice;
    @JsonProperty("usdPrice")
    private Double usdPrice;
    @JsonProperty("exchangeAddress")
    private String exchangeAddress;
    @JsonProperty("exchangeName")
    private String exchangeName;
}
