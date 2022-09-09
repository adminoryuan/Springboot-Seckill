package com.example.seckill_boot;

import com.example.seckill_boot.Impl.DbSkillServerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeckillBootApplicationTests {

    @Autowired
    DbSkillServerImpl im;
    @Test
    void contextLoads() {
        im.Skill(1);
    }

}
