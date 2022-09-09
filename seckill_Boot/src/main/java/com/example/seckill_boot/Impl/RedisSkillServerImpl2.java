package com.example.seckill_boot.Impl;

import com.example.seckill_boot.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author yh
 * @date 2022/9/7 下午12:38
 */
@Service(value = "RedisSkillServerImpl2")
public class RedisSkillServerImpl2 implements SkillService {

    private String skillKey="Skill_key";
    private String OkSet="Skill_okset";

    private String script="local userid=KEYS[1];\n" +
            "local qtkey=\"Skill_key\";\n" +
            "local userskey=\"Skill_okset\";\n" +
            "local userExists=redis.call(\"sismember\",userskey,userid);\n" +
            "if tonumber(userExists)==1 then\n" +
            "  return 2;\n" +
            "end\n" +
            "local num=redis.call(\"get\",qtkey);\n" +
            "if tonumber(num)<=0 then\n" +
            "  return 0;\n" +
            "else\n" +
            "  redis.call(\"decr\",qtkey);\n" +
            "  redis.call(\"sadd\",userskey,userid);\n" +
            "  end\n" +
            "  return 1\n";

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 基于redis lua脚本实现 推荐！！
     * @param userid
     * @return
     */
    @Override
    public boolean Skill(int userid){

        Long res = redisTemplate.execute(
                new DefaultRedisScript<Long>(script, Long.class)
                , Arrays.asList(String.valueOf(userid)),
                String.valueOf(userid));
        if (res==0) {
            System.out.println("已抢空！");
            return false;
        } else if (res==1) {
            System.out.println("抢购成功");
            return true;
        } else if (res==2) {
            System.out.println("该用户已抢过！");
            return false;
        } else {
            System.out.println("抢购异常");
            return false;
        }
    }

}
