package com.example.seckill_boot.Controller;

import com.example.seckill_boot.SkillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yh
 * @date 2022/9/7 下午12:38
 */
@RestController
public class Skill_Controller {
    private AtomicInteger integer=new AtomicInteger();


    @Resource(name = "DbSkillServerImpl")
   // @Resource(name = "RedisSkillServerImpl")

    SkillService skillServer;
    @GetMapping("/Skill")

    public String Skill(){
        integer.set(integer.get()+1);
        return String.valueOf(integer.get() +"-" +((skillServer.Skill(integer.get()))?"秒杀成功":"秒杀失败"));
    }
}
