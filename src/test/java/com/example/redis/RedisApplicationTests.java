package com.example.redis;

import com.example.redis.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {


    /**
     * 操作key-value都是字符串
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 操作key-value都是对象
     */
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 自定义序列后的RedisTemplate
     */
    @Autowired
    RedisTemplate<Object, Object> newRedisTemplate;

    /**
     * 测试保存
     * redis常见数据类型：string-字符串  list-列表 set-集合 hash-散列 zset-有序集合
     * stringRedisTemplate.opsForValue() 操作字符串
     * stringRedisTemplate.opsForList() 操作列表
     * stringRedisTemplate.opsForSet() 操作集合
     * stringRedisTemplate.opsForHash() 操作散列
     * stringRedisTemplate.opsForZet() 操作有序集合
     */
    @Test
    public void testSave() {
         stringRedisTemplate.opsForValue().append("token","123");
    }

    /**
     * 测试读取
     */
    @Test
    public void testRead() {
        String token = stringRedisTemplate.opsForValue().get("token");
        System.out.println(token);
    }

    /**
     * 自定序列化redisTemplate 写入
     */
    @Test
    public void testNewSave(){
        User user = new User();
        user.setId(1);
        user.setName("jack");
        user.setAge(20);
        newRedisTemplate.opsForValue().set("user-one",user);

    }

    /**
     * 自定序列化redisTemplate 读取
     */
    @Test
    public void testNewRead() {
        String token = newRedisTemplate.opsForValue().get("user-one").toString();
        System.out.println(token);
    }
}
