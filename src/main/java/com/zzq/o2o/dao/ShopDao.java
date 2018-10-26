package com.zzq.o2o.dao;

import com.zzq.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店鋪信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
