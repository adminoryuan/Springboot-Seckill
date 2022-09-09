package com.example.seckill_boot.Impl;

import com.example.seckill_boot.SkillService;
import com.example.seckill_boot.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yh
 * @date 2022/9/7 下午1:41
 */
@Service(value = "DbSkillServerImpl")
public class DbSkillServerImpl  implements SkillService{

    @Autowired
    StockMapper stockMapper;
    private int proid=12;

    /**
     * 基于db实现
     * @param userid
     * @return
     */
    @Override
    @Transactional
    public boolean Skill(int userid) {
        int stock = stockMapper.getStock(proid);

        System.out.println("stock"+stock);
        if (stock<=0){
            System.out.println("库存不足");
            return false;
        }
        if (stockMapper.Exsit(userid,proid)>0){
            System.out.println("你已经秒杀过了!!");
            return false;
        }
        if (stockMapper.skill(proid,stock-1)>0){
            if (stockMapper.AddSecode(userid,proid)>0){
                System.out.println("秒杀成功");
                return true;
            }
        }
        return   false;
    }
}
