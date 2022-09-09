package com.example.seckill_boot.Impl;

import com.example.seckill_boot.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yh
 * @date 2022/9/7 下午12:38
 */
@Service(value="RedisSkillServerImpl")
public class RedisSkillServerImpl implements SkillService {

    private String skillKey="Skill_key";
    private String OkSet="Skill_okset";

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 基于redis 乐观锁实现 会出现库存遗留问题
     * @param userid
     * @return
     */
    public boolean Skill(int userid){


        return redisTemplate.execute(new SessionCallback<Object>(){
                @Override
                public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                    redisTemplate.watch(skillKey);
                    if (Long.parseLong(redisTemplate.opsForValue().get(skillKey))<=0) {
                        return null;
                    }
                    operations.multi();
                    redisTemplate.opsForValue().decrement(skillKey);

                    redisTemplate.opsForSet().add(OkSet,String.valueOf(userid));
                    return operations.exec();
                }
        })!=null;


    }

}
