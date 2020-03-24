package com.li.missyou.services;

import com.li.missyou.vo.SpuDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpuServiceTest {
    @Autowired
    private SpuService spuService;

    @Test
    void getSpuDetailByID() {
        SpuDetailVO spu = spuService.getSpuDetailByID(1L);
        System.out.println(spu.toString());
    }
}