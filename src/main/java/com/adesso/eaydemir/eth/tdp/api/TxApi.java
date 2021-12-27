package com.adesso.eaydemir.eth.tdp.api;

import com.adesso.eaydemir.eth.tdp.document.Tx;
import com.adesso.eaydemir.eth.tdp.repository.filter.TxFilter;
import com.adesso.eaydemir.eth.tdp.service.TxService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Transaction Data Provider", description = "Transaction data for addresses")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/txs")
public class TxApi {

    private static final List<String> ADDRESSES = new ArrayList<>();

    static {
        ADDRESSES.add("0x27066d336c9f25c2477cc5a319ab37ba5f5ca508");
        ADDRESSES.add("0xa7728a59522af8ebc762b20e682d0cc4b1a36e1f");
    }

    private final TxService txService;

    @GetMapping("/searchable-addresses")
    public ResponseEntity<List<String>> getSearchableAddresses() {
        return ResponseEntity.ok(ADDRESSES);
    }

    @GetMapping
    public ResponseEntity<Page<Tx>> findAllByCustomFilter(@ModelAttribute TxFilter txFilter,
                                                          @RequestParam int pageSize,
                                                          @RequestParam int page) {
        return ResponseEntity.ok(txService.findAllByCustomFilter(txFilter, PageRequest.of(page, pageSize)));
    }

    @GetMapping("/{hash}")
    public ResponseEntity<Tx> getTxsByHash(@PathVariable("hash") String hash) {
        return ResponseEntity.ok(txService.findByHash(hash));
    }

    @GetMapping("/addresses/{address}")
    public ResponseEntity<Page<Tx>> getTxsByAddress(@PathVariable("address") String address,
                                                    @RequestParam int pageSize,
                                                    @RequestParam int page) {
        return ResponseEntity.ok(txService.findByAddress(address, PageRequest.of(page, pageSize)));
    }

    @PutMapping("/addresses/{address}")
    public ResponseEntity<Void> saveTxsOfAddress(@PathVariable("address") String address) {
        if (!ADDRESSES.contains(address)) {
            ADDRESSES.add(address);
            try {
                txService.saveTxsOfAddress(address);
            } catch (Exception ex) {
                ADDRESSES.remove(address);
                return ResponseEntity.internalServerError().build();
            }
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/addresses/{address}")
    public ResponseEntity<Void> deleteTxsOfAddress(@PathVariable("address") String address) {
        ADDRESSES.remove(address);
        txService.deleteByAddress(address);
        return ResponseEntity.ok().build();
    }
}
