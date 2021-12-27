package com.adesso.eaydemir.eth.tdp.repository;

import com.adesso.eaydemir.eth.tdp.document.Tx;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TxRepository extends ElasticsearchRepository<Tx, String>, CustomTxRepository {

    Page<Tx> findByFromAddressOrToAddressAllIgnoreCase(String from, String to, Pageable pageable);

    Page<Tx> findByBlockTimestampBetween(Date from, Date to, Pageable pageable);

    Page<Tx> findByValueGreaterThan(BigDecimal value, Pageable pageable);

    @Query("{\"match\": {\"hash\": {\"query\": \"?0\"}}}")
    Optional<Tx> findByHashWithSensitiveCase(String hash);

    void deleteByFromAddressOrToAddressAllIgnoreCase(String from, String to);
}
