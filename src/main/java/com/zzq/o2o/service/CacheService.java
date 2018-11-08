package com.zzq.o2o.service;

public interface CacheService {

	/**
	 * 依据key前缀删除匹配该模式下的所有key-value都会被清空
	 * @param keyPrefix
	 */
	void removeFromCache(String keyPrefix);
}
