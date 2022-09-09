local userid=KEYS[1]; //读取用户id
local qtkey="Skill_key"; //商品的key
local userskey="Skill_okset"; //保存秒杀成功用户的集合
local userExists=redis.call("sismember",userskey,userid);
if tonumber(userExists)==1 then
  return 2;
end
local num=redis.call("get",qtkey);
if tonumber(num)<=0 then
  return 0;
else
  redis.call("decr",qtkey);
  redis.call("sadd",userskey,userid);
  end
  return 1
