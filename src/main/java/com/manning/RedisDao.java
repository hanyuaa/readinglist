package com.manning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by hanyu on 2018/4/19.
 */
@Repository
public class RedisDao {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public  void setKey(String key,String value){
        ValueOperations<String, String> value1 = redisTemplate.opsForValue();
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        return ops.get(key);
    }
}
