package com.adesso.eaydemir.eth.tdp.repository;

import com.adesso.eaydemir.eth.tdp.document.Tx;
import com.adesso.eaydemir.eth.tdp.repository.filter.TxFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomTxRepository {

    Page<Tx> findAllByCustomFilter(TxFilter txFilter, Pageable pageable);
}
