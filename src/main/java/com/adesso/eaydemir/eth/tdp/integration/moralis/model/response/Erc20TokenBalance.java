package com.adesso.eaydemir.eth.tdp.integration.moralis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Erc20TokenBalance {
    @JsonProperty("token_address")
    private String tokenAddress;
    @JsonProperty("name")
    private String name;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("logo")
    private String logo;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("decimals")
    private String decimals;
    @JsonProperty("balance")
    private String balance;
}
