package com.adesso.eaydemir.eth.tdp.repository.impl;

import com.adesso.eaydemir.eth.tdp.document.Tx;
import com.adesso.eaydemir.eth.tdp.repository.CustomTxRepository;
import com.adesso.eaydemir.eth.tdp.repository.filter.TxFilter;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Service
@RequiredArgsConstructor
public class CustomTxRepositoryImpl implements CustomTxRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    private static void addQuery(QueryBuilder queryBuilder, NativeSearchQueryBuilder nativeSearchQueryBuilder) {
        nativeSearchQueryBuilder.withQuery(queryBuilder);
    }

    private static NativeSearchQuery build(NativeSearchQueryBuilder nativeSearchQueryBuilder, Pageable pageable) {
        if (pageable != null)
            return nativeSearchQueryBuilder.withPageable(pageable).build();
        else
            return nativeSearchQueryBuilder.build();
    }

    @Override
    public Page<Tx> findAllByCustomFilter(TxFilter txFilter, Pageable pageable) {
        final NativeSearchQueryBuilder nativeSearchQueryBuilder = prepareQuery();
        if (txFilter.getHash() != null)
            addQuery(matchQuery("hash", txFilter.getHash()).operator(Operator.AND), nativeSearchQueryBuilder);
        if (txFilter.getMinValue() != null)
            addQuery(boolQuery().should(rangeQuery("value").gte(txFilter.getMinValue())), nativeSearchQueryBuilder);
        if (txFilter.getFromBlockTimestamp() != null && txFilter.getToBlockTimestamp() != null)
            addQuery(boolQuery().should(rangeQuery("blockTimestamp").lte(txFilter.getToBlockTimestamp())
                    .gte(txFilter.getFromBlockTimestamp())), nativeSearchQueryBuilder);
        if (txFilter.getAddress() != null) {
            if (!txFilter.getType().equals(TxFilter.Type.ALL)) {
                if (txFilter.getType().equals(TxFilter.Type.IN))
                    addQuery(matchQuery("fromAddress", txFilter.getAddress()).operator(Operator.AND), nativeSearchQueryBuilder);
                else
                    addQuery(matchQuery("toAddress", txFilter.getAddress()).operator(Operator.AND), nativeSearchQueryBuilder);
            } else {
                addQuery(matchQuery("fromAddress", txFilter.getAddress()).operator(Operator.OR), nativeSearchQueryBuilder);
                addQuery(matchQuery("toAddress", txFilter.getAddress()).operator(Operator.OR), nativeSearchQueryBuilder);
            }
        }
        NativeSearchQuery nativeSearchQuery = build(nativeSearchQueryBuilder, pageable);
        SearchHits<Tx> txSearchHits = elasticsearchOperations.search(nativeSearchQuery, Tx.class);
        return new PageImpl<>(txSearchHits.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList()), pageable, txSearchHits.getTotalHits());
    }

    private NativeSearchQueryBuilder prepareQuery() {
        return new NativeSearchQueryBuilder();
    }
}
