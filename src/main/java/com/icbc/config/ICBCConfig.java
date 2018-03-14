package com.icbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="session")
public class ICBCConfig {

	public Long redisExpire;
	
	public String encryptKey;

	public Long getRedisExpire() {
		return redisExpire;
	}

	public String getEncryptKey() {
		return encryptKey;
	}

	public void setRedisExpire(Long redisExpire) {
		this.redisExpire = redisExpire;
	}

	public void setEncryptKey(String encryptKey) {
		this.encryptKey = encryptKey;
	}

}
