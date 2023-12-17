package com.company.project.utils;

import com.company.project.vo.LockVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService {

    public void setData(String key, Long value);

    public void set(String key, Object value);

    public void setWithExpire(String key, Object value, long time);

    public List<String> batchGet(List<String> keyList);

    /**
     * 根据 redis  的key的集合批量查询对应存在数据
     * @param keys
     * @param useParallel  是否使用parallel 在没有顺序要求的时候,提高效率,true为表示使用,false 表示不用,默认为true
     * @return
     */
    public Map<String,Object> batchQueryByKeys(List<String> keys, Boolean useParallel);

    /**
     * 按照天
     * @param key
     * @param value
     * @param time
     */
    public void setDay(String key, Object value, long time);

    public Object get(String key);

    public Long increment(String key, long l);

    void HMSet(String str, String key, Object value, boolean isExpire);

    void HMSet(String str, String key, Object value, long hours);

    Long HMincrement(String str, String key, long l);

    Object HMGet(String str, String key);

    boolean existKey(String key);

    boolean HMexistKey(String key, Object obj);

    void delete(String key);

    /**
     * 批量删除多个key
     * @param key
     */
    void batchDelete(String... key);

    void HMDelete(String str1, String str2);

    /**
     * 获取key得过期时间 秒计算
     * @param key
     * @return
     */
    public Long expire(String key);

    /**
     * 获取key得过期时间 秒计算
     * @param key
     * @return
     */
    public Long expireMillisecond(String key);

    void expire(String key, long minute);
    /**
     * 获取二级key-value中存在于Collection中的key的value
     * @param user
     * @param key
     * @return
     */
    List<Object> multiGet(String user, Collection<Object> key);

    /**
     * 获取二级key-value中的所有value值
     */
    List<Object> getValues(String key);

    /**
     * 模糊查找符合条件的key
     */
    Set<String> like(String pattern);

    Set<Object> getSet(String key);

    public void put(String redisKey, String key, Object doamin, long expire);
//
//	public Object get(String redisKey,String key);
//
//	public void remove(String redisKey,String key);

    public void setData(String key, String value, Long timeOut, TimeUnit timeUnit);

    public boolean tryLock(LockVo lock);

    public boolean getLock(LockVo lock, long timeout, long tryInterval, long lockExpireTime);

    public void releaseLock(LockVo lock);
}
