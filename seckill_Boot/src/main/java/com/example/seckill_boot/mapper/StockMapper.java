package com.example.seckill_boot.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author yh
 * @date 2022/9/7 下午1:50
 */

@Mapper
public interface StockMapper {

    @Select("select stock from stock where productid=#{proid} for update")
    int getStock(int proid);

    @Update("update stock set stock=#{stock} where productId=#{proid}")
    int skill(int proid,int stock);

    @Select("select count(*) from Skill_Secode where userid=#{uid} and proid=#{pid} for update")

    int Exsit(int uid,int pid);

    @Insert("insert into Skill_Secode(userid,proid) values(#{userid},#{proid})")
    int AddSecode(int userid,int proid);
}
