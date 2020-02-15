package com.jdtech.multidimensional.service.redis;

import java.util.Set;

/***
 @author echo
 @create 2019年07月03日 11:58

 */
public interface RedisServiceApi {
    /***
     * 保存对象 通过key
     * 并且设置过期时间 这个时间不能为复数
     * 单位是 : 秒
     */
    <T> void redisSaveObjectByKey(String key, T t, long expireTime);

    /***
     * 保存对象 通过key
     *
     */
    <T> void redisSaveObjectByKey(String key, T t);

    /***
     * 判断key是否存在
     * @param key
     * @return
     */
    boolean redisKeyIfExist(String key);

    /***
     * 通过key获得对应的值
     * @param key
     * @return
     */
    <T> T redisGetValueByKey(String key);

    /***
     * 生成的唯一的key值 这个值会做是否唯一的判断
     * 名字的组成格式:Table Name: Primary Key Name: Primary Key Value: Column Name
     * @param author
     * @param packageName
     * @param columnName
     * @return key 值
     */
    String redisGenerateUniqueKeyValue(String author, String packageName, String columnName);

    /***
     * 通过一个对象删除数据
     * @param key 值
     */
    boolean redisDeleteObjByKey(String key);

    /**
     * 删除多个key值
     * @param key
     * @return
     */
    long redisDeleteObjByKey(String... key);

    /**
     * 根据key的正则表达返回对应的key值
     * @param keys
     * @return
     */
    Set<String> redisGetObjByKeysPattern(String keys);
}
