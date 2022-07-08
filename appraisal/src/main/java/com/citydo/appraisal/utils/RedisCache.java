package com.citydo.appraisal.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
public class RedisCache {

    private static RedisTemplate<String,Object> redisTemplate;

    private static StringRedisTemplate stringRedisTemplate;

    public static void setIfAbsent(String key, Object value, Long timeout) {
        redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 保存缓存
     *
     * @param key
     * @param value
     * @return
     */
    public static void put(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存缓存
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public static void put(String key, Object value, Long timeout) {
        put(key, value, timeout, TimeUnit.SECONDS);

    }

    /**
     * 保存缓存
     *
     * @param key
     * @param value
     * @param expireAt
     * @return
     */
    public static void put(String key, Object value, Date expireAt) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expireAt(key, expireAt);
    }


    /**
     * 获取key的过期剩余时间 秒
     * @param key
     * @return
     */
    public static Long getExpire(String key) {
        return redisTemplate.opsForValue().getOperations().getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    public static void put(String key, Object value, Long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 往hash中添加字段和值
     *
     * @param key
     * @param field
     * @param value
     */
    public static void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 缓存失效时间设置
     *
     * @param key
     * @param timeout 失效时间 ,单位s
     * @return
     */
    public static void expire(String key, Long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 缓存失效时间设置
     *
     * @param key
     * @param date 失效时间
     * @return
     */
    public static void expire(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 获取缓存
     *
     * @return
     */
    public static Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取hash中field字段的值
     *
     * @param key
     * @param field
     * @return
     */
    public static Object hget(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取hash值
     *
     * @param key
     * @return
     */
    public static Map<Object, Object> hentries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取hash中所有的field
     *
     * @param key
     * @return
     */
    public static Set<Object> hkeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取hash中所有的value
     *
     * @param key
     * @return
     */
    public static List<Object> hvalues(String key) {
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 该hash中是否存在field
     *
     * @param key
     * @param field
     * @return
     */
    public static boolean hasKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return 是否删除成功
     */
    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个缓存
     *
     * @param keys
     * @return 成功删除数量
     */
    public static Long delete(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    /**
     * 删除hash中field缓存
     *
     * @param key
     * @return 成功删除数量
     */
    public static Long hdelete(String key, List<String> fields) {
        return redisTemplate.opsForHash().delete(key, fields.toArray());
    }

    /**
     * 计数器加1
     *
     * @param key
     * @return
     */
    public static Long increment(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }

    /**
     * 计数器加1
     *
     * @param key
     * @param timeout 超时时间
     * @return
     */
    public static Long increment(String key, Date timeout) {
        long c = stringRedisTemplate.opsForValue().increment(key);
        expire(key, timeout);
        return c;
    }

    /**
     * 计数器加1
     *
     * @param key
     * @param delta   步长
     * @param timeout 超时时间
     * @return
     */
    public static Long increment(String key, long delta, Date timeout) {
        long c = stringRedisTemplate.opsForValue().increment(key, delta);
        expire(key, timeout);
        return c;
    }

    /**
     * 计数器以指定步长递增
     *
     * @param key   计数器key
     * @param delta 自增步长
     * @return
     */
    public static Long increment(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * hash中field的值 计数器加1
     *
     * @param key
     * @param field
     * @param delta
     * @return
     */
    public static Long hincrement(String key, String field, long delta) {
        return stringRedisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 计数器减1
     *
     * @param key
     * @return
     */
    public static Long decrement(String key) {
        return stringRedisTemplate.opsForValue().decrement(key);
    }

    public static Long decrement(String key, Date timeout) {
        long c = stringRedisTemplate.opsForValue().decrement(key);
        expire(key, timeout);
        return c;
    }

    /**
     * 计数器以指定步长递减
     *
     * @param key   计数器key
     * @param delta 递减步长
     * @return
     */
    public static Long decrement(String key, long delta) {
        return stringRedisTemplate.opsForValue().decrement(key, delta);
    }

    /**
     * 从队列右边移除一个元素并返回该元素
     *
     * @param key
     * @return java.lang.Object
     * @author zhanglihui
     * @date 2020-11-06 10:42
     */
    public static String rightPop(String key) {
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 从队列左边插入一个元素
     *
     * @param key
     * @param value
     * @return java.lang.Object
     * @author zhanglihui
     * @date 2020-11-06 10:42
     */
    public static void leftPush(String key, String value) {
        stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取列表长度
     *
     * @param key
     * @return java.lang.Long
     * @author zhanglihui
     * @date 2020-11-06 10:46
     */
    public static Long listSize(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /**
     * 获取列表中的数据
     *
     * @param key
     * @param start
     * @param end
     * @return java.util.List<java.lang.Object>
     * @author zhanglihui
     * @date 2020-11-06 10:47
     */
    public static List<String> listRange(String key, int start, int end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 移除并获取列表头部第一个元素
     *
     * @param key
     * @return
     */
    public static String lpop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 往列表尾部插入一个元素
     *
     * @param key
     * @param value
     */
    public static void rpush(String key, String value) {
        stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 批量查询
     *
     * @param keys 查询主键列表
     */
    public static List<Object> multiGet(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Autowired
    public static void setRedisTemplate(RedisTemplate<String,Object> redisTemplate) {
        RedisCache.redisTemplate = redisTemplate;
    }

    @Autowired
    public static void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisCache.stringRedisTemplate = stringRedisTemplate;
    }
}
