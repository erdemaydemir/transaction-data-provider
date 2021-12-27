package com.adesso.eaydemir.eth.tdp.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TxFilter {

    private String hash;
    private String address;
    private Type type;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fromBlockTimestamp;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date toBlockTimestamp;
    private BigDecimal minValue;

    public enum Type {
        IN, OUT, ALL
    }
}
