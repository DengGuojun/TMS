package com.lpmas.tms.waybill.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.tms.config.TmsRedisConfig;
import com.lpmas.tms.factory.TmsRedisFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

public class WaybillRedisDao {

	private static Logger logger = LoggerFactory.getLogger(WaybillRedisDao.class);

	public long getWaybillCountByDate(String date) {
		long count = 0;
		String key = TmsRedisConfig.getWaybillNumberKey(date);
		TmsRedisFactory factory = new TmsRedisFactory();
		JedisPool jedisPool = null;
		Jedis jedis = null;
		Transaction tx = null;
		try {
			jedisPool = factory.getRedisPool();
			jedis = jedisPool.getResource();
			tx = jedis.multi();
			tx.incr(key);
			List<Object> result = tx.exec();
			if (result != null && !result.isEmpty()) {
				count = (long) result.get(0);
			}
		} catch (Exception e) {
			logger.error("", e);
			if (tx != null) {
				tx.discard();
			}
			throw e;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return count;
	}

}
