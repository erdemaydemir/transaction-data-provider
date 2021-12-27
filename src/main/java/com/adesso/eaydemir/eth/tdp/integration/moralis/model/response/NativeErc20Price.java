package com.adesso.eaydemir.eth.tdp.integration.moralis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NativeErc20Price {
    @JsonProperty("value")
    private String value;
    @JsonProperty("decimals")
    private Integer decimals;
    @JsonProperty("name")
    private String name;
    @JsonProperty("symbol")
    private String symbol;
}
