package com.lpmas.tms.factory;


import com.lpmas.framework.nosql.redis.RedisFactory;
import com.lpmas.framework.nosql.redis.RedisInitializer;
import com.lpmas.tms.config.TmsRedisConfig;

import redis.clients.jedis.JedisPool;

public class TmsRedisFactory extends RedisFactory {
	@Override
	public JedisPool getRedisPool() {
		return (JedisPool) RedisInitializer.getRedisPool(TmsRedisConfig.REDIS_NAME);
	}
}
