package com.example.demo.redis;

import com.example.demo.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


/*
封装Redis的常用操作
 */
@Component
public class RedisTools {

    @Autowired
    private RedisTemplate redisTemplate;





    /*
    数据插入
     */
    public boolean set(String key,Staff value){
        boolean result = false;
        try{
            redisTemplate.opsForValue().set(key,value);
            result=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    /*
    数据插入
     */
    public boolean setList(String key, List<Staff> staffs){
        boolean result = false;
        try{
            redisTemplate.opsForValue().set(key,staffs);
            result=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    /*
     * 判断缓存中是否有对应的value
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /*
    获取数据
     */
    public Staff get(String key){
        Staff result;
        try{
            result= (Staff) redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            result= null;
            e.printStackTrace();
        }
        return result;
    }

    /*
   获取数据
    */
    public List<Staff> getList(String key){
        List<Staff> result;
        try{
            result= (List<Staff>) redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            result= null;
            e.printStackTrace();
        }
        return result;
    }

    /*
     删除数据
     */
    public boolean delete(String key){
        boolean result;
        try{
            redisTemplate.delete(key);
            result=true;
        }catch (Exception e){
            result=false;
            e.printStackTrace();
        }
        return result;
    }
}

