package me.twocat.shareinfo.util.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String , Object> redisTemplate;

    /***
     * 默认的过期时间 单位:秒
     * 24 hours
     */
    public static final long DEFAULT_EXPIRE=60 * 60 * 24 ;

    /***
     * 不设置过期时间
     */
    public static final long NOT_EXPIRE = -1;

    /***
     * 判断是否存在key值
     * redis的数据库中
     * @param key
     * @return
     */
    public boolean exitesKey(String key){
        return  redisTemplate.hasKey(key);
    }

    /***
     * 重命名key
     * 如果newkey已经存在 则用newkey来覆盖
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey , String newKey){
        redisTemplate.rename(oldKey , newKey);
    }

    /**
     * newkey重新命名 如果它不存在
     * @param oldKey
     * @param newKey
     * @return
     */
    public boolean renameKeyNotExist(String oldKey , String newKey){
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key值
     * @param key
     */
    public void deleteKey(String key){
        redisTemplate.delete(key);
    }

    /***
     * 删除多个key值
     * @param keys
     */
    public void deleteKey(String... keys){
        Set<String> kSet = Stream.of(keys).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /***
     * 删除collection集合的key值
     * @param keys
     */
    public void deleteKey(Collection<String> keys){
        Set<String> kSet = keys.stream().collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 设置key值的 生命周期
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key , long time , TimeUnit timeUnit){
        redisTemplate.expire(key, time, timeUnit);
    }

    /***
     * 根据时间的设置指定某一个过期的时间点
     * @param key
     * @param date
     */
    public void expireKeyAt(String key , Date date){
        redisTemplate.expireAt(key, date);
    }

    /***
     * 查询key的生命周期
     * @param key
     * @param timeUnit
     * @return
     */
    public long getKeyExpire(String key , TimeUnit timeUnit){
        return redisTemplate.getExpire(key, timeUnit);
    }


    /**
     * 设置key永久有效
     * @param key
     */
    public void persistKey(String key){
        redisTemplate.persist(key);
    }
}
