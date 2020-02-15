package me.twocat.shareinfo.service.redis;

import com.jdtech.multidimensional.service.redis.RedisServiceApi;
import me.twocat.shareinfo.util.redis.RedisKeyUtil;
import me.twocat.shareinfo.util.redis.RedisUtils;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.plugin2.util.PojoUtil;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/***
 @author echo
 @create 2019年07月03日 14:08

 */
@Service
public  class RedisServiceApiImpl implements RedisServiceApi {

    @Resource
    private RedisTemplate<String , Object> redisTemplate;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private ValueOperations<String,Object> valueOperations;
    @Resource
    private HashOperations<String, String, Object> hashOperations;
    @Resource
    private ListOperations<String, Object> listOperations;
    @Resource
    private SetOperations<String, Object> setOperations;
    @Resource
    private ZSetOperations<String, Object> zSetOperations;



    @Override
    public <T> void redisSaveObjectByKey(String key, T t, long expireTime) {
        if(expireTime <= 0){
            expireTime =RedisUtils.DEFAULT_EXPIRE;
        }
        redisTemplate.opsForValue().set(key , t);
        //设置这个key的过期时间 单位是秒
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public <T> void redisSaveObjectByKey(String key, T t) {
        redisTemplate.opsForValue().set(key , t );
    }

    @Override
    public boolean redisKeyIfExist(String key) {
        return redisUtils.exitesKey(key);
    }

    @Override
    public String redisGenerateUniqueKeyValue(String tableName, String majorKey, String majorKeyValue) {
        String key = RedisKeyUtil.getKey(tableName, majorKey, majorKeyValue);
        if (redisKeyIfExist(key))
        {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 5;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            String appendKey = buffer.toString();
            key += appendKey;
            if(redisKeyIfExist(key)){
                redisGenerateUniqueKeyValue(tableName , majorKey+ appendKey, majorKeyValue);
            }
        }
        return key;
    }

    @Override
    public boolean redisDeleteObjByKey(String key) {
        return   redisTemplate.delete(key);
    }

    @Override
    public long redisDeleteObjByKey(String... keys) {
        return redisTemplate.delete(Arrays.asList(keys));
    }

    @Override
    public Set<String> redisGetObjByKeysPattern(String s) {
        return redisTemplate.keys(s);
    }

    @Override
    public Object redisGetValueByKey(String key) {
        Object onj = null;
        if(StringUtils.hasText(key))
        {
             onj = redisTemplate.opsForValue().get(key);
        }
        return  onj;
    }
}
