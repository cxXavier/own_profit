package com.xavier.fast.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/5/30.
 */
@Component
public class RedisClient {
	
	private static final Logger log = LoggerFactory.getLogger(RedisClient.class);

	@Autowired
	private JedisPool jedisPool;

	public Long incrRedis(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incr(key);
		} catch (Exception e) {
			log.error("incrRedis EROOR",e);
			return null;
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	public Long incrRedisWhitNum(String key,Long num,Integer db){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (db != null) {
				jedis.select(db);
			}
			return jedis.incrBy(key,num);
		} catch (Exception e) {
			log.error("incrRedis EROOR",e);
			return null;
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	public Long decrRedisWhitNum(String key,Long num,Integer db){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (db != null) {
				jedis.select(db);
			}
			return jedis.decrBy(key,num);
		} catch (Exception e) {
			log.error("decrRedis EROOR",e);
			return null;
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	public void incrRedisWhitDB(String key, Long seconds, Integer dbIndex) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (dbIndex != null) {
				jedis.select(dbIndex);
			}
			jedis.incr(key);
			if (seconds != null) {
				jedis.expire(key, seconds.intValue());
			}

		} catch (Exception e) {
			log.error("incrRedisWhitDB EROOR",e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	public String getRedis(String key) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.get(key);
		} catch (Exception e) {
			log.error("getRedis ERROR", e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}

	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			log.error("set ERROR",e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	public void delWithDB(String key, int db) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (db > 0) {
				jedis.select(db);
			}
			jedis.del(key);
		} catch (Exception e) {
			log.error("del ERROR",e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}

	public void setWithDB(String key, String value, Long seconds, Integer dbIndex) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (dbIndex != null) {
				jedis.select(dbIndex);
			}
			jedis.set(key, value);
			if (seconds != null) {
				jedis.expire(key, seconds.intValue());
			}
		} catch (Exception e) {
			log.error("setWithDB ERROR",e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
	}

	public String getRedisWhitDB(String key, Integer dbIndex) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = jedisPool.getResource();
			if (dbIndex != null) {
				jedis.select(dbIndex);
			}

			result = jedis.get(key);
		} catch (Exception e) {
			log.error("getRedisWhitDB ERROR", e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}
	
	public void PfAdd(String key,String value,Integer dbIndex,Long seconds){
    	Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
			if (dbIndex != null) {
				jedis.select(dbIndex);
			}
			jedis.pfadd(key, value);
			if (seconds != null) {
				jedis.expire(key, seconds.intValue());
			}
        } catch (Exception e) {
			log.error("PfAdd ERROR", e);
		} finally {
            // 返还到连接池
			if(null != jedis){
				jedis.close();
			}
        }
    }
	
	 /**
     * 方法描述:redis HyperLogLog输入元素
     * 
     * @author leon 2017年8月28日 下午2:39:17
     * @param dbIndex redis数据库
     * @param key
     * @param value
     */
    public void pfadd(Integer dbIndex, String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (dbIndex != null) {
                jedis.select(dbIndex);
            }
            jedis.pfadd(key, value);
        } catch (Exception e) {
			log.error("pfadd ERROR", e);
		} finally {
            // 返还到连接池
            if (jedis != null) {
                jedis.close();
            }
        }
    }
	
	public Integer pfCount(String key,Integer dbIndex){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			if (dbIndex != null) {
				jedis.select(dbIndex);
			}
			return (int) jedis.pfcount(key);
		} catch (Exception e) {
			log.error("pfCount ERROR", e);
			return 0;
		} finally {
			// 返还到连接池
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	/**
	 * 获取哈希里面的内容
	 * @author 董朱旭
	 * @time 2017/11/24
	 * @CopyRight 杭州微财网络科技有限公司
	 * @param key
	 * @param dbIndex
	 * @return java.lang.String
	 */
	public String hGet(String key,String field,Integer dbIndex){
		Jedis jedis = null;
		try {
			 jedis = jedisPool.getResource();
			if (dbIndex != null) {
				jedis.select(dbIndex);
			}
			return  jedis.hget(key,field);
		} finally {
			// 返还到连接池
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	/**
     * 存入 set 集合
     * @author 董朱旭
     * @time 2017/11/22
     * @CopyRight 杭州微财网络科技有限公司
     * @param key
     * @param value
     * @param dbIndex
     * @param seconds
     * @return void
     */
    public void sAdd(String key,String value,Integer dbIndex,Long seconds){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (dbIndex != null) {
                jedis.select(dbIndex);
            }
            jedis.sadd(key, value);
            if (seconds != null) {
                jedis.expire(key, seconds.intValue());
            }
        } catch (Exception e) {
			log.error("sAdd ERROR", e);
		} finally {
            // 返还到连接池
			if(null != jedis){
				jedis.close();
			}
        }
    }

    /**
     * 存入 set 集合
     * @author 董朱旭
     * @time 2017/11/22
     * @CopyRight 杭州微财网络科技有限公司
     * @param key
     * @param value
     * @param dbIndex
     * @return void
     */
    public void sAdd(String key,String value,Integer dbIndex){
    	Jedis jedis = null;
    	try {
    		jedis = jedisPool.getResource();
    		if (dbIndex != null) {
    			jedis.select(dbIndex);
    		}
    		jedis.sadd(key, value);
    	} catch (Exception e) {
    		log.error("sAdd ERROR", e);
    	} finally {
    		// 返还到连接池
			if(null != jedis){
				jedis.close();
			}
    	}
    }
    /**
	 * 获取 所有成员 set
	 * @author 董朱旭
	 * @time 2017/11/22
	 * @CopyRight 杭州微财网络科技有限公司
	 * @param key
	 * @param dbIndex
	 * @return java.util.Set<java.lang.String>
	 */
	public Set<String> smembers(String key, Integer dbIndex) {
		Jedis jedis = null;
		Set<String> result = new HashSet<>();
		try {
			jedis = jedisPool.getResource();
			if (dbIndex != null) {
				jedis.select(dbIndex);
			}

			result = jedis.smembers(key);
		} catch (Exception e) {
			log.error("smembers ERROR", e);
		} finally {
			if(null != jedis){
				jedis.close();
			}
		}
		return result;
	}


	/**
	 * 删除哈希结构里面的某个域
	 * @author 董朱旭
	 * @time 2017/12/13
	 * @CopyRight 杭州微财网络科技有限公司
	 * @param key
	 * @param filed
	 * @param value
	 * @param dbIndex
	 * @return void
	 */
	public void hset(String key,String filed,String value,Integer dbIndex, Long seconds){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//选择数据库
			if (dbIndex != null) {
				jedis.select(dbIndex);
			}

			jedis.hset(key,filed,value);
			//若是有时间限制
			if(seconds!=null){
				jedis.expire(key.toString(), seconds.intValue());
			}
		}catch (Exception e){
			log.error("hset ERROR",e);
		}finally {
			// 返还到连接池
			if(null != jedis){
				jedis.close();
			}
		}
	}
}
