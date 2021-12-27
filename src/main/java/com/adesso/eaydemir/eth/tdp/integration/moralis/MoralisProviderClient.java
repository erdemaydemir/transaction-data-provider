package com.adesso.eaydemir.eth.tdp.integration.moralis;

import com.adesso.eaydemir.eth.tdp.integration.moralis.config.MoralisProviderClientConfiguration;
import com.adesso.eaydemir.eth.tdp.integration.moralis.model.request.ChainList;
import com.adesso.eaydemir.eth.tdp.integration.moralis.model.response.Erc20TokenBalance;
import com.adesso.eaydemir.eth.tdp.integration.moralis.model.response.Erc20Transaction;
import com.adesso.eaydemir.eth.tdp.integration.moralis.model.response.NativeBalance;
import com.adesso.eaydemir.eth.tdp.integration.moralis.model.response.TransactionCollection;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@FeignClient(value = "moralis", url = "${feign.client.config.moralis.apiUrl}", configuration = MoralisProviderClientConfiguration.class)
public interface MoralisProviderClient {

    @GetMapping(value = "/{address}", produces = "application/json")
    TransactionCollection getTransactions(@PathVariable("address") String address, @RequestParam(value = "chain", required = false) ChainList chain,
                                          @RequestParam(value = "subdomain", required = false) String subdomain,
                                          @RequestParam(value = "from_block", required = false) Integer fromBlock,
                                          @RequestParam(value = "to_block", required = false) Integer toBlock,
                                          @RequestParam(value = "offset", required = false) Integer offset,
                                          @RequestParam(value = "limit", required = false) Integer limit);

    @GetMapping(value = "/{address}/balance", produces = "application/json")
    NativeBalance getNativeBalance(@PathVariable("address") String address, @RequestParam(value = "chain", required = false) ChainList chain,
                                   @RequestParam(value = "providerUrl", required = false) String providerUrl,
                                   @RequestParam(value = "to_block", required = false) BigDecimal toBlock);

    @GetMapping(value = "/{address}/erc20", produces = "application/json")
    List<Erc20TokenBalance> getTokenBalances(@PathVariable("address") String address, @RequestParam(value = "chain", required = false) ChainList chain,
                                             @RequestParam(value = "subdomain", required = false) String subdomain,
                                             @RequestParam(value = "to_block", required = false) BigDecimal toBlock);

    @GetMapping(value = "/{address}/erc20/transfers", produces = "application/json")
    List<Erc20Transaction> getTokenTransfers(@PathVariable("address") String address, @RequestParam(value = "chain", required = false) ChainList chain,
                                             @RequestParam(value = "subdomain", required = false) String subdomain,
                                             @Valid @RequestParam(value = "from_block", required = false) Integer fromBlock,
                                             @RequestParam(value = "to_block", required = false) Integer toBlock,
                                             @RequestParam(value = "offset", required = false) Integer offset,
                                             @RequestParam(value = "limit", required = false) Integer limit);

}
