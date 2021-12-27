package com.adesso.eaydemir.eth.tdp.service;

import com.adesso.eaydemir.eth.tdp.document.Tx;
import com.adesso.eaydemir.eth.tdp.integration.moralis.MoralisProviderClient;
import com.adesso.eaydemir.eth.tdp.integration.moralis.model.request.ChainList;
import com.adesso.eaydemir.eth.tdp.integration.moralis.model.response.TransactionCollection;
import com.adesso.eaydemir.eth.tdp.repository.TxRepository;
import com.adesso.eaydemir.eth.tdp.repository.filter.TxFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TxService {

    private static final Logger logger = LoggerFactory.getLogger(TxService.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final DozerBeanMapper DOZER_BEAN_MAPPER = new DozerBeanMapper();

    private final TxRepository txRepository;
    private final MoralisProviderClient moralisProviderClient;

    @PostConstruct
    public void init() throws IOException {
        initialTxDataInserter();
    }

    private void initialTxDataInserter() throws IOException {
        OBJECT_MAPPER.registerModule(new ParameterNamesModule());
        StringBuilder sb = new StringBuilder();
        ClassPathResource txsResource = new ClassPathResource("data/tx-datas.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(txsResource.getInputStream()));
        bufferedReader.lines().forEach(sb::append);
        List<Tx> txs = OBJECT_MAPPER.readValue(sb.toString(), new TypeReference<>() {
        });
        this.save(txs);
    }

    private void save(List<Tx> txs) {
        txRepository.saveAll(txs);
    }

    public Tx findByHash(String hash) {
        return txRepository.findById(hash).orElseThrow();
    }

    public Page<Tx> findByAddress(String address, Pageable pageable) {
        return txRepository.findByFromAddressOrToAddressAllIgnoreCase(address, address, pageable);
    }

    public void deleteByAddress(String address) {
        txRepository.deleteByFromAddressOrToAddressAllIgnoreCase(address, address);
    }

    public void saveTxsOfAddress(String address) {
        TransactionCollection transactions = moralisProviderClient.getTransactions(address, ChainList.ETH, null, null, null, null, null);
        List<Tx> txs = transactions.getResult().stream().map(transaction -> DOZER_BEAN_MAPPER.map(transaction, Tx.class)).collect(Collectors.toList());
        txRepository.saveAll(txs);
    }

    public Page<Tx> findAllByCustomFilter(TxFilter txFilter, Pageable pageable) {
        return txRepository.findAllByCustomFilter(txFilter, pageable);
    }
}
