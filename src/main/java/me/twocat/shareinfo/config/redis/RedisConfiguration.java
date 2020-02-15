package me.twocat.shareinfo.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    /***
     * spring RedisReceiver provides an implemention for Spring cache abstraction through the org.spring...RedisReceiver.cache package
     * to use RedisReceiver as a backing implementation,simply add RedisCacheManager to your configuration
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory)
    {
        return RedisCacheManager.create(redisConnectionFactory);

    }


    /****
     * redistemple 相关配置
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String , Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String , Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //Use Jackson 2Json RedisSerializer to serialize and deserialize the value of RedisReceiver (default JDK serialization)
        //使用Jackson 来序列化和反序列redis的数据(默认的是jdk序列化)
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //指定要序列化的域 field，get和set 还有修饰符的范围 any是包括所有的private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定序列化的输入类型 类必须是非final修饰的  final修饰的类 比如string ， Integer会抛出异常
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        //值使用json序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //使用stringredisserializer来序列化和反序列化redis的key值   StringRedisSerializer这个来序列化和反序列是人类可以理解的
        //JsonRedisSerializer这个是jdk默认的序列化的包 这个是纯的二进制的文件 不可视 这样的话就是key是人类刻度的而对应的数据确是 二进制的文件不可读
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置hash的key和value 原理同上
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return  redisTemplate;
    }

    /***
     * 对redis的hash类型的数据操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public HashOperations<String,String,Object> hashOperations(RedisTemplate<String , Object> redisTemplate){
        return redisTemplate.opsForHash();
    }

    /***
     * 对redis 的字符串进行操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public ValueOperations<String , Object> valueOperations(RedisTemplate<String , Object>  redisTemplate){
        return redisTemplate.opsForValue();
    }

    /***
     * 对redis的链表型数据类型进行操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public ListOperations<String , Object> listOperations(RedisTemplate<String , Object> redisTemplate){
        return redisTemplate.opsForList();
    }

    /***
     * 对redis的无序集合类型进行操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public SetOperations<String , Object> setOperations(RedisTemplate<String , Object> redisTemplate){
        return redisTemplate.opsForSet();
    }

    /***
     * 对redis的有序集合类型进行操作
     * @param redisTemplate
     * @return
     */
    @Bean
    public ZSetOperations<String , Object> zSetOperations(RedisTemplate<String, Object> redisTemplate){
        return redisTemplate.opsForZSet();
    }
}
