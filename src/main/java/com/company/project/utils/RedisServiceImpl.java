package com.company.project.utils;

import com.company.project.vo.LockVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class RedisServiceImpl implements RedisService {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Resource
	private RedisTemplate stringRedisTemplate;


	//锁状态
	private static final String LOCK_SUCCESS = "OK";

	//释放状态
	private static final Long RELEASE_SUCCESS = 1L;

	private final static long LOCK_EXPIRE = 30 * 1000L;//单个业务持有锁的时间30s，防止死锁
	private final static long LOCK_TRY_INTERVAL = 30L;//默认30ms尝试一次
	private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;//默认尝试20s

//	@Resource
//	protected HashOperations<String, String, Object> hashOperations;

	@Override
	public List<String> batchGet(List<String> keyList) {

		if(null == keyList || keyList.size() == 0 ){
			return null;
		}

		/* 批量获取多条数据 */
		List<Object> objects = stringRedisTemplate.executePipelined(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
				StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
				for (String key : keyList) {
					stringRedisConnection.get(key);
				}
				return null;
			}
		});

		List<String> collect = objects.stream().map(val -> String.valueOf(val)).collect(Collectors.toList());

		return collect;
	}

	@Override
	public Map<String,Object> batchQueryByKeys(List<String> keys, Boolean useParallel) {
		if(null == keys || keys.size() == 0 ){
			return null;
		}

		if(null == useParallel){
			useParallel = true;
		}

		List<Object> results = stringRedisTemplate.executePipelined(
				new RedisCallback<Object>() {
					public Object doInRedis(RedisConnection connection) throws DataAccessException {
						StringRedisConnection stringRedisConn = (StringRedisConnection)connection;
						for(String key:keys) {
							System.out.println("get方式获取token " +get(key));
							System.out.println("管道获取token " + stringRedisConn.get(key));
						}
						return null;
					}
				});
		if(null == results || results.size() == 0 ){return null;}

		Map<String,Object> resultMap  =  null;
		if(useParallel){

			Map<String,Object> resultMapOne  = Collections.synchronizedMap(new HashMap<String,Object>());

			keys.parallelStream().forEach(t -> {
				resultMapOne.put(t,results.get(keys.indexOf(t)));
			});

			resultMap = resultMapOne;

		}else{

			Map<String,Object> resultMapTwo  = new HashMap<>();

			for(String t:keys){
				resultMapTwo.put(t,results.get(keys.indexOf(t)));
			}

			resultMap = resultMapTwo;
		}
		return resultMap;
	}


	/**
	 * 批量删除多个key
	 * @param key
	 */
	@Override
	public void batchDelete(String... key) {
		if (key != null && key.length > 0){
			if (1 == key.length){
				redisTemplate.delete(key[0]);
			}else {
				redisTemplate.delete(Arrays.asList(key));
			}
		}
	}

	@Override
	public void setData(String key, Long value) {
		Logger.info(this, "setdata :" + key + " : " + value);
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
		vo.increment(key, value);
	}

	@Override
	public void set(String key, Object value) {
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);

	}

	@Override
	public Long expire(String key) {
		return redisTemplate.getExpire(key,TimeUnit.SECONDS);
	}

	@Override
	public Long expireMillisecond(String key) {
		return redisTemplate.getExpire(key,TimeUnit.MILLISECONDS);
	}

	@Override
	public void setWithExpire(String key, Object value, long time){
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value);
        if(time!=-1){
			this.expire(key, time);
		}
	}

	/**
	 * 按照天
	 * @param key
	 * @param value
	 * @param time
	 */
	@Override
	public void setDay(String key, Object value, long time){
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
		vo.set(key, value);
		if(time!=-1){
			this.expireDay(key, time);
		}
	}

	@Override
	public Object get(String key) {
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
	}

	@Override
	public Long increment(String key, long l) {
		return redisTemplate.opsForValue().increment(key, l);
	}

	@Override
	public void HMSet(String str,String key, Object value,boolean isExpire) {
		redisTemplate.opsForHash().put(str, key, value);
		if(isExpire){
			expireSeconds(str,60*60*2);
		}
	}

	@Override
	public void HMSet(String str,String key, Object value,long hours) {
		redisTemplate.opsForHash().put(str, key, value);
		expireHours(str,hours);
	}

	@Override
	public Long HMincrement(String str,String key, long l) {
		Long increment = redisTemplate.opsForHash().increment(str, key, l);
		return increment;
	}


	@Override
	public Object HMGet(String str,String key){
		return redisTemplate.opsForHash().get(str, key);
	}

	//按毫秒
	@Override
	public void expire(String key, long minute){
		redisTemplate.expire(key, minute, TimeUnit.MILLISECONDS);
	}

	//按秒
	public void expireSeconds(String key,long minute){
		redisTemplate.expire(key, minute, TimeUnit.SECONDS);
	}

	//按小时
	public void expireHours(String key,long hours){
		redisTemplate.expire(key, hours, TimeUnit.HOURS);
	}

	//按天
	public void expireDay(String key,long hours){
		redisTemplate.expire(key, hours, TimeUnit.DAYS);
	}

	@Override
	public boolean existKey(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public boolean HMexistKey(String key,Object obj) {
		return redisTemplate.opsForHash().hasKey(key,obj);
	}

	@Override
	public void delete(String key) {
		if(existKey(key)){
			redisTemplate.delete(key);
		}
	}

	@Override
	public void HMDelete(String str1, String str2){
		HashOperations<String, Object, Object> operation = redisTemplate.opsForHash();
		if(operation.hasKey(str1, str2)){
			operation.delete(str1, str2);
		}
	}

	@Override
	public List<Object> multiGet(String user, Collection<Object> key){
		return redisTemplate.opsForHash().multiGet(user, key);
	}
	/**
	 * 获取二级key-value中的所有value值
	 */
	@Override
	public List<Object> getValues(String key){
		return redisTemplate.opsForHash().values(key);
	}
	/**
	 * 模糊查找符合条件的key
	 */
	@Override
	public Set<String> like(String pattern){
		return redisTemplate.keys(pattern);
	}

	@Override
	public Set<Object> getSet(String key) {
		SetOperations<String,Object> vo = redisTemplate.opsForSet();
		return vo.members(key);
	}

	/**
	 * 添加
	 *
	 * @param key    key
	 * @param doamin 对象
	 * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
	 */
	@Override
	public void put(String redisKey, String key, Object doamin, long expire) {
		redisTemplate.opsForHash().put(redisKey, key, doamin);

		if (expire != -1) {
			redisTemplate.expire(redisKey, expire, TimeUnit.SECONDS);
		}
	}

	/**
	 * 查询
	 *
	 * @param key 查询的key
	 * @return
	 */
//	public Object get(String redisKey,String key) {
//		return hashOperations.get(redisKey, key);
//	}

	/**
	 * 删除
	 *
	 * @param key 传入key的名称
	 */
//	public void remove(String redisKey,String key) {
//		hashOperations.delete(redisKey, key);
//	}

	@Override
	public void setData(String key, String value, Long timeOut, TimeUnit timeUnit) {
		Logger.info(this, "setdata :" + key + " : " + value);
		redisTemplate.opsForValue().set(key, value,timeOut,timeUnit);
	}

	@Override
	public boolean tryLock(LockVo lock) {
		return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
	}

	@Override
	public boolean getLock(LockVo lock, long timeout, long tryInterval, long lockExpireTime) {
		try {
			if (StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue())) {
				return false;
			}
			long startTime = System.currentTimeMillis();
			do{
				if (!redisTemplate.hasKey(lock.getName())) {
					ValueOperations<String, Object> ops = redisTemplate.opsForValue();
					//毫秒
					ops.set(lock.getName(), lock.getValue(), lockExpireTime, TimeUnit.MILLISECONDS);
					return true;
				} else {//存在锁
					Logger.debug(this,"lock is exist!！！");
				}
				if (System.currentTimeMillis() - startTime > timeout) {//尝试超过了设定值之后直接跳出循环
					return false;
				}
				Thread.sleep(tryInterval);
			}
			while (redisTemplate.hasKey(lock.getName())) ;
		} catch (InterruptedException e) {
			Logger.error(this,"getLock error:"+e);
			return false;
		}
		return false;
	}

	@Override
	public void releaseLock(LockVo lock) {
		if (!StringUtils.isEmpty(lock.getName())) {
			delete(lock.getName());
		}
	}



}
