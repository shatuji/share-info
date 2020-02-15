package me.twocat.shareinfo.util.redis;

/***
 * 对于redis的key值设计
 */
public class RedisKeyUtil {


    /***
     * redis的key值得组成是:Table Name: Primary Key Name: Primary Key Value: Column Name
     * @param tableName
     * @param majorKey
     * @param majorKeyValue
     * @param column
     * @return
     */
    public static String getKeyWithColumn(String tableName , String majorKey , String majorKeyValue ,String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column).append(":");
        return buffer.toString();
    }

    /**
     * RedisReceiver 的key值组成是:
     * Table Name: Primary Key Name: Primary Key Value
     *
     * @param tableName Table name
     * @param majorKey key name
     * @param majorKeyValue primary key
     * @return
     */
    public static String getKey(String tableName,String majorKey,String majorKeyValue){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        return buffer.toString();
    }
}
