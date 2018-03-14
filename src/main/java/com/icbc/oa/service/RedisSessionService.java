package com.icbc.oa.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.icbc.config.ICBCConfig;
import com.icbc.config.ICBCSession;

@Component
@Service("redisSessionService")
@EnableAutoConfiguration
public class RedisSessionService {
	
	/**
	 * 配置参数化
	 */
	@Autowired 
	ICBCConfig icbcConfig;
	
	@Autowired
	RedisTemplate<String, String> redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	/**
	 * 延长redis session 有效期
	 * 
	 * @param key
	 * @return
	 */
	public Boolean setSessionExpire(String key) {
		return redisTemplate.expire(key, icbcConfig.getRedisExpire(), TimeUnit.SECONDS);
	}

	/**
	 * 刷新缓存时间
	 * 获取保存到 redis 服务器 的 session
	 * 
	 * @param soaCert
	 * @param sysNo
	 * @param userId
	 * @return
	 */
	public JSONObject getSession(String key) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		setSessionExpire(key);
		return JSONObject.parseObject(opsForValue.get(key));
	}
//	public JSONObject getSession(String soaCert, String sysNo, String userId) {
//		String key = soaCert + "_" + sysNo;
//		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
//		// 创建缓存数据存储
//		return JSONObject.parseObject(opsForValue.get(key));
//	}

	/**
	 * 生成 session 保存到redis服务器
	 * 
	 * @param soaCert
	 * @param sysNo
	 * @param userId
	 */
//	public JSONObject createSession(String soaCert, String sysNo, String userId) {
//		String key = soaCert + "_" + sysNo;
//		// 构建user对象
//		JSONObject user = createUser(soaCert, sysNo, userId);
//		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
//		// 创建缓存数据存储
//		opsForValue.set(key, user.toString(), ICBCConfig.redisExpire, TimeUnit.SECONDS);
//		System.out.println("增加 valueFromRedis 取值： " + user.toString());
//		return user;
//	}

	/**
	 * 清除保存到redis服务器 的 session
	 * 
	 * @param soaCert
	 * @param sysNo
	 * @param userId
	 */
	public JSONObject removeSession(String key) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		JSONObject user = JSONObject.parseObject(opsForValue.get(key));
		// 清除缓存数据存储
		del(key);
		return user;
	}

	/**
	 * 根据情况批量删除保存到redis服务器 的 session
	 * 
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	/**
	 * 根据情况模糊匹配批量删除保存到redis服务器 的 session
	 * 
	 * @param key
	 */
	public void batchDel(String... pattern) {
		for (String kp : pattern) {
			redisTemplate.delete(redisTemplate.keys(kp + "*"));
		}
	}

	/**
	 * 创建新的用户SESSION信息
	 * 
	 * @param soaCert
	 * @param sysNo
	 * @param userId
	 * @return
	 */
//	private JSONObject createUser(String soaCert, String sysNo, String userId) {
//		JSONObject user = new JSONObject();
//		user.put("userId", userId);
//		user.put("soaCert", soaCert);
//		user.put("sysNo", sysNo);
//		return user;
//	}

	/**
	 * 
	 * @param key
	 * @param icbcSession
	 * @return
	 */
	public Object addObject(String key, ICBCSession icbcSession) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, JSONObject.toJSON(icbcSession).toString(), icbcConfig.getRedisExpire(), TimeUnit.SECONDS);
		System.out.println("增加 valueFromRedis 取值： " + JSONObject.toJSON(icbcSession).toString());
		return icbcSession;
	}

}