package org.kingsy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest///在测试类上添加了这个注释，那么将来单元测试方法执行之前会先初始化Spring容器
public class RedisTest {
    @Autowired
    private StringRedisTemplate sd;
    @Test
    public  void testSet(){
        ValueOperations<String, String> operations= sd.opsForValue();

        operations.set("username","zhangsa");
        operations.set("id","1",5, TimeUnit.SECONDS);


    }
    @Test
    public  void testGet(){
       ValueOperations<String,String> operations= sd.opsForValue();
        System.out.println(operations.get("username"));
    }
}
