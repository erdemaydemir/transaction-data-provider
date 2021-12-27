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

@Tag(name = "Transaction Data Provider", description = "Transaction data for addresses")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/txs")
public class TxApi {

    private final TxService txService;

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
        try {
            txService.saveTxsOfAddress(address);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/addresses/{address}")
    public ResponseEntity<Void> deleteTxsOfAddress(@PathVariable("address") String address) {
        txService.deleteByAddress(address);
        return ResponseEntity.ok().build();
    }
}
