package com.adesso.eaydemir.eth.tdp;

import com.adesso.eaydemir.eth.tdp.document.Tx;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TransactionDataProviderApplicationTests {

    @Test
    void contextLoads() {
        Tx tx = new Tx();
        assertNotNull(tx);
    }

}
