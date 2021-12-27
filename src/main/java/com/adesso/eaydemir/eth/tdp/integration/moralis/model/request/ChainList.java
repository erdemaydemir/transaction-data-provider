package com.adesso.eaydemir.eth.tdp.integration.moralis.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ChainList {
    ETH("eth");

    private String value;

    ChainList(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ChainList fromValue(String text) {
        for (ChainList b : ChainList.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
